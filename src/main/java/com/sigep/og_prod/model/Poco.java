package com.sigep.og_prod.model;

import com.opencsv.bean.CsvBindByName;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "pocos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Poco {

    @Id
    @CsvBindByName(column = "POCO")
    @Column(name = "poco", length = 100)
    private String poco;

    @CsvBindByName(column = "CADASTRO")
    @Column(name = "cadastro")
    private String cadastro;

    @CsvBindByName(column = "OPERADOR")
    @Column(name = "operador")
    private String operador;

    @CsvBindByName(column = "POCO_OPERADOR")
    @Column(name = "poco_operador")
    private String pocoOperador;

    @CsvBindByName(column = "ESTADO")
    @Column(name = "estado", length = 50)
    private String estado;

    @CsvBindByName(column = "BACIA")
    @Column(name = "bacia")
    private String bacia;

    @CsvBindByName(column = "BLOCO")
    @Column(name = "bloco")
    private String bloco;

    @CsvBindByName(column = "SIG_CAMPO")
    @Column(name = "sig_campo")
    private String sigCampo;

    @CsvBindByName(column = "CAMPO")
    @Column(name = "campo")
    private String campo;

    @CsvBindByName(column = "TERRA_MAR")
    @Column(name = "terra_mar", length = 50)
    private String terraMar;

    @CsvBindByName(column = "POCO_POS_ANP")
    @Column(name = "poco_pos_anp")
    private String pocoPosAnp;

    @CsvBindByName(column = "TIPO")
    @Column(name = "tipo")
    private String tipo;

    @CsvBindByName(column = "CATEGORIA")
    @Column(name = "categoria")
    private String categoria;

    @CsvBindByName(column = "RECLASSIFICACAO")
    @Column(name = "reclassificacao")
    private String reclassificacao;

    @CsvBindByName(column = "SITUACAO")
    @Column(name = "situacao")
    private String situacao;

    @CsvBindByName(column = "INICIO")
    @Column(name = "inicio")
    private String inicio;

    @CsvBindByName(column = "TERMINO")
    @Column(name = "termino")
    private String termino;

    @CsvBindByName(column = "CONCLUSAO")
    @Column(name = "conclusao")
    private String conclusao;

    @CsvBindByName(column = "TITULARIDADE")
    @Column(name = "titularidade")
    private String titularidade;

    @CsvBindByName(column = "LATITUDE_BASE_4C")
    @Column(name = "latitude_base_4c")
    private String latitudeBase4c;

    @CsvBindByName(column = "LONGITUDE_BASE_4C")
    @Column(name = "longitude_base_4c")
    private String longitudeBase4c;

    @CsvBindByName(column = "LATITUDE_BASE_DD")
    @Column(name = "latitude_base_dd")
    private String latitudeBaseDd;

    @CsvBindByName(column = "LONGITUDE_BASE_DD")
    @Column(name = "longitude_base_dd")
    private String longitudeBaseDd;

    @CsvBindByName(column = "DATUM_HORIZONTAL")
    @Column(name = "datum_horizontal")
    private String datumHorizontal;

    @CsvBindByName(column = "TIPO_DE_COORDENADA_DE_BASE")
    @Column(name = "tipo_de_coordenada_de_base")
    private String tipoDeCoordenadaDeBase;

    @CsvBindByName(column = "DIRECAO")
    @Column(name = "direcao")
    private String direcao;

    @CsvBindByName(column = "PROFUNDIDADE_VERTICAL_M")
    @Column(name = "profundidade_vertical_m")
    private String profundidadeVerticalM;

    @CsvBindByName(column = "PROFUNDIDADE_SONDADOR_M")
    @Column(name = "profundidade_sondador_m")
    private String profundidadeSondadorM;

    @CsvBindByName(column = "PROFUNDIDADE_MEDIDA_M")
    @Column(name = "profundidade_medida_m")
    private String profundidadeMedidaM;

    @CsvBindByName(column = "REFERENCIA_DE_PROFUNDIDADE")
    @Column(name = "referencia_de_profundidade")
    private String referenciaDeProfundidade;

    @CsvBindByName(column = "MESA_ROTATIVA")
    @Column(name = "mesa_rotativa")
    private String mesaRotativa;

    @CsvBindByName(column = "COTA_ALTIMETRICA_M")
    @Column(name = "cota_altimetrica_m")
    private String cotaAltimetricaM;

    @CsvBindByName(column = "LAMINA_D_AGUA_M")
    @Column(name = "lamina_d_agua_m")
    private String laminaDAguaM;

    @CsvBindByName(column = "DATUM_VERTICAL")
    @Column(name = "datum_vertical")
    private String datumVertical;

    @CsvBindByName(column = "UNIDADE_ESTRATIGRAFICA")
    @Column(name = "unidade_estratigrafica")
    private String unidadeEstratigrafica;

    @CsvBindByName(column = "GEOLOGIA_GRUPO_FINAL")
    @Column(name = "geologia_grupo_final")
    private String geologiaGrupoFinal;

    @CsvBindByName(column = "GEOLOGIA_FORMACAO_FINAL")
    @Column(name = "geologia_formacao_final")
    private String geologiaFormacaoFinal;

    @CsvBindByName(column = "GEOLOGIA_MEMBRO_FINAL")
    @Column(name = "geologia_membro_final")
    private String geologiaMembroFinal;

    @CsvBindByName(column = "CDPE")
    @Column(name = "cdpe")
    private String cdpe;

    @CsvBindByName(column = "AGP")
    @Column(name = "agp")
    private String agp;

    @CsvBindByName(column = "PC")
    @Column(name = "pc")
    private String pc;

    @CsvBindByName(column = "PAG")
    @Column(name = "pag")
    private String pag;

    @CsvBindByName(column = "PERFIS_CONVENCIONAIS")
    @Column(name = "perfis_convencionais")
    private String perfisConvencionais;

    @CsvBindByName(column = "DURANTE_PERFURACAO")
    @Column(name = "durante_perfuracao")
    private String durantePerfuracao;

    @CsvBindByName(column = "PERFIS_DIGITAIS")
    @Column(name = "perfis_digitais")
    private String perfisDigitais;

    @CsvBindByName(column = "PERFIS_PROCESSADOS")
    @Column(name = "perfis_processados")
    private String perfisProcessados;

    @CsvBindByName(column = "PERFIS_ESPECIAIS")
    @Column(name = "perfis_especiais")
    private String perfisEspeciais;

    @CsvBindByName(column = "AMOSTRA_LATERAL")
    @Column(name = "amostra_lateral")
    private String amostraLateral;

    @CsvBindByName(column = "SISMICA")
    @Column(name = "sismica")
    private String sismica;

    @CsvBindByName(column = "TABELA_TEMPO_PROFUNDIDADE")
    @Column(name = "tabela_tempo_profundidade")
    private String tabelaTempoProfundidade;

    @CsvBindByName(column = "DADOS_DIRECIONAIS")
    @Column(name = "dados_direcionais")
    private String dadosDirecionais;

    @CsvBindByName(column = "TESTE_A_CABO")
    @Column(name = "teste_a_cabo")
    private String testeACabo;

    @CsvBindByName(column = "TESTE_DE_FORMACAO")
    @Column(name = "teste_de_formacao")
    private String testeDeFormacao;

    @CsvBindByName(column = "CANHONEIO")
    @Column(name = "canhoneio")
    private String canhoneio;

    @CsvBindByName(column = "TESTEMUNHO")
    @Column(name = "testemunho")
    private String testemunho;

    @CsvBindByName(column = "GEOQUIMICA")
    @Column(name = "geoquimica")
    private String geoquimica;

    @CsvBindByName(column = "SIG_SONDA")
    @Column(name = "sig_sonda")
    private String sigSonda;

    @CsvBindByName(column = "NOM_SONDA")
    @Column(name = "nom_sonda")
    private String nomSonda;

    @CsvBindByName(column = "ATINGIU_PRESAL")
    @Column(name = "atingiu_presal")
    private String atingiuPresal;

    @CsvBindByName(column = "DHA_ATUALIZACAO")
    @Column(name = "dha_atualizacao")
    private String dhaAtualizacao;

}
