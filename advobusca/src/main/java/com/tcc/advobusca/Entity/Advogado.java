package com.tcc.advobusca.Entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.tcc.advobusca.Enums.Status;

@Entity
public class Advogado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "cod_oab")
    private String codOab;

    @Column(name = "cpf")
    private String cpf;

    @Column(name = "nome")
    private String nome;

    @Column(name = "rg")
    private String rg;

    @Column(name = "telefone_adv")
    private String telefoneAdv;

    @Column(name = "genero")
    private String genero;

    @Column(name = "valor")
    private String valor;
    
	@Column(name = "email")
    private String email;

    @Column(name = "senha")
    private String senha;

    @Column(name = "dataNascimento")
    private String dataNascimento;

    @Column(name = "cep")
    private String cep;

    @Column(name = "cidade")
    private String cidade;

    @Column(name = "bairro")
    private String bairro;

    @Column(name = "rua")
    private String rua;
    
    @Column(name = "statusAdvogado")
    @Enumerated(EnumType.STRING)
    private Status statusAdvogado;

    @Column(name = "numero")
    private String numero;

    @Column(name = "complemento")
    private String complemento;

    @Column(name = "pontoDeReferencia")
    private String pontoDeReferencia;
    
    @Column(name = "foto", length=8000, nullable=true)
	private byte[] foto;

    @ManyToMany
    @JoinTable(name = "Advogado_AtuacaoAdvogado",
               joinColumns = @JoinColumn(name = "advogado_id"),
               inverseJoinColumns = @JoinColumn(name = "areaatuacao_id"))
    private List<AreaAtuacao> areasDeAtuacao;
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
	public String getCodOab() {
		return codOab;
	}
	public void setCodOab(String codOab) {
		this.codOab = codOab;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getRg() {
		return rg;
	}
	public void setRg(String rg) {
		this.rg = rg;
	}
	public String getTelefoneAdv() {
		return telefoneAdv;
	}
	public void setTelefoneAdv(String telefoneAdv) {
		this.telefoneAdv = telefoneAdv;
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	
    public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getRua() {
		return rua;
	}
	public void setRua(String rua) {
		this.rua = rua;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	public String getPontoDeReferencia() {
		return pontoDeReferencia;
	}
	public void setPontoDeReferencia(String pontoDeReferencia) {
		this.pontoDeReferencia = pontoDeReferencia;
	}
	public byte[] getFoto() {
		return foto;
	}
	public void setFoto(byte[] foto) {
		this.foto = foto;
	}
	public Status getStatusAdvogado() {
		return statusAdvogado;
	}
	public void setStatusAdvogado(Status statusAdvogado) {
		this.statusAdvogado = statusAdvogado;
	}
}
