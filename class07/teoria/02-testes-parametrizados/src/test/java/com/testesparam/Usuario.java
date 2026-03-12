package com.testesparam;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.regex.Pattern;

public class Usuario {

    private String nomeCompleto;
    private String emailAcesso;
    private String chaveCriptografada;
    private String padraoCriptografia;

    private static final int LIMITE_FALHAS = 3;
    private static final long JANELA_TEMPO_MILIS = 30000;
    private static final long TEMPO_BLOQUEIO_MILIS = 60000;

    private final List<Long> historicoDeFalhas = new ArrayList<>();
    private long limiteDoBloqueio = 0;

    private static final Pattern REGRA_NOME = Pattern.compile("^[A-Za-z ]+$");
    private static final Pattern REGRA_EMAIL = Pattern.compile("^[A-Za-z0-9_.]+@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)+$");

    public Usuario(String loginTemporario, String senhaTemporaria) {
        this.nomeCompleto = loginTemporario;
        definirSenha(senhaTemporaria);
    }

    public void definirSenha(String senha) {
        checarTextoVazio(senha, "Senha");
        this.padraoCriptografia = "SHA-256";
        try {
            this.chaveCriptografada = gerarCriptografia(senha, this.padraoCriptografia);
        } catch (NoSuchAlgorithmException erro) {
            throw new RuntimeException("Falha crítica: Algoritmo SHA-256 não localizado.", erro);
        }
    }

    public boolean validarAcesso(String senhaTentativa) throws ExceededAttemptsException {
        long momentoAtual = System.currentTimeMillis();

        if (momentoAtual < limiteDoBloqueio) {
            throw new ExceededAttemptsException("Acesso negado: Conta temporariamente suspensa.");
        }

        String hashCalculado;
        try {
            hashCalculado = gerarCriptografia(senhaTentativa, this.padraoCriptografia);
        } catch (NoSuchAlgorithmException erro) {
            throw new RuntimeException("Falha no motor de criptografia", erro);
        }

        if (this.chaveCriptografada.equals(hashCalculado)) {
            historicoDeFalhas.clear();
            limiteDoBloqueio = 0;
            return true;
        } else {
            registrarErroDeLogin(momentoAtual);
            return false;
        }
    }

    public boolean validarAcessoComAlgoritmoCustomizado(String senha, String algoritmo) throws NoSuchAlgorithmException {
        String hashCalculado = gerarCriptografia(senha, algoritmo);
        return this.chaveCriptografada.equals(hashCalculado) && this.padraoCriptografia.equals(algoritmo);
    }

    private String gerarCriptografia(String texto, String algoritmo) throws NoSuchAlgorithmException {
        try {
            MessageDigest motorDigest = MessageDigest.getInstance(algoritmo);
            byte[] bytesHasheados = motorDigest.digest(texto.getBytes("UTF-8"));
            return Base64.getEncoder().encodeToString(bytesHasheados);
        } catch (NoSuchAlgorithmException erro) {
            throw erro;
        } catch (Exception erro) {
            throw new RuntimeException("Erro inesperado na geração do Hash", erro);
        }
    }

    private void registrarErroDeLogin(long momentoFalha) throws ExceededAttemptsException {
        historicoDeFalhas.removeIf(tempoAntigo -> (momentoFalha - tempoAntigo) > JANELA_TEMPO_MILIS);
        historicoDeFalhas.add(momentoFalha);

        if (historicoDeFalhas.size() >= LIMITE_FALHAS) {
            limiteDoBloqueio = momentoFalha + TEMPO_BLOQUEIO_MILIS;
            historicoDeFalhas.clear();
            throw new ExceededAttemptsException("Múltiplas falhas detectadas. Conta travada por 1 minuto.");
        }
    }

    private void checarTextoVazio(String valor, String nomeCampo) {
        if (valor == null || valor.trim().isEmpty()) {
            throw new IllegalArgumentException("O campo " + nomeCampo + " não aceita valores nulos ou em branco.");
        }
    }

    public void alterarNome(String nome) {
        checarTextoVazio(nome, "Nome");
        if (!REGRA_NOME.matcher(nome).matches()) throw new IllegalArgumentException("Nome possui caracteres inválidos.");
        this.nomeCompleto = nome.trim();
    }

    public void alterarEmail(String email) {
        checarTextoVazio(email, "E-mail");
        if (!REGRA_EMAIL.matcher(email).matches()) throw new IllegalArgumentException("E-mail fora do padrão estabelecido.");
        this.emailAcesso = email;
    }

    public String getNomeCompleto() { return nomeCompleto; }
    public String getEmailAcesso() { return emailAcesso; }
    public String getChaveCriptografada() { return chaveCriptografada; }
    public boolean isBloqueado() { return System.currentTimeMillis() < limiteDoBloqueio; }
    public int getQuantidadeFalhas() { return historicoDeFalhas.size(); }
}