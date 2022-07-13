package com.ex.geografando.Conexao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Conexao extends SQLiteOpenHelper {

    private static final String banco = "quiz";
    private static final int version =105;

    public Conexao(Context context) {
        super(context, banco, null, version);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        criaTabelaCategoria(db);
        criarTabelaPerguntasPartidas(db);
        criarTabelaConquistas(db);
        criarTabelaPerguntaTemp(db);
        criarTabelaAlternativasDisponiveisJogoPendente(db);
        criarTabelaAlternativas(db);
        criarTabelaJogoPendente(db);
        criarTabelaCidades(db);
        criarTabelaPais(db);
        criarTabelaPartidaTemporaria(db);
        criarTabelaPartida(db);
        criarTabelaQuantidadePerguntas(db);
        criarTabelaParametros(db);
        criarTabelaSequenciaVitorias(db);
        criarTabelaConquistasSemCategorias(db);
        criarTabelaAcertosContinentes(db);
        criaTabelaHabilitaQtdPerguntas(db);

        popularTabelaConquistas(db);
        popularTabelaSequenciaVitorias(db);
        popularTabelaQuantidadePerguntas(db);
        popularTabelaJogoPendente(db);
        popularTabelaParametros(db);
        popularTabelaAlternativas1(db);
        popularTabelaAlternativas2(db);
        popularTabelaCategorias(db);
        popularTabelaCidades1(db);
        popularTabelaCidades2(db);
        popularTabelaPais(db);
        popularTabelaConquistasSemCategoria(db);
        popularTabelaAcertosContinenes(db);
        popularTabelaHabilitaQtdPerguntas(db);
    }



    private void criarTabelaAcertosContinentes(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS `acertosContinentes` (\n" +
                "  ID int(1) NOT NULL DEFAULT '0',\n" +
                "  continente varchar(100) DEFAULT NULL,\n" +
                "  qtd int(8) DEFAULT NULL,\n" +
                "  PRIMARY KEY (`ID`)\n" +
                ")");
    }    private void criarTabelaConquistasSemCategorias(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS `conquistas_sem_categorias` (\n" +
                "  ID INTEGER(2) NOT NULL DEFAULT '0',\n" +
                "  conquista varchar(100) DEFAULT NULL,\n" +
                "  qtd_desbloqueio int(11) DEFAULT NULL,\n" +
                "  desbloqueio int(1) DEFAULT NULL,\n" +
                "  PRIMARY KEY (`ID`)\n" +
                ")");
    }
    private void criarTabelaSequenciaVitorias(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS `sequenciaVitorias` (\n" +
                "  `ID` int(1) NOT NULL DEFAULT '0',\n" +
                "  `DESCRICAO` varchar(30) DEFAULT NULL,\n" +
                "  `SEQUENCIA_ATUAL` int(6) DEFAULT NULL,\n" +
                "  `SEQUENCIA_MAXIMA` int(6) DEFAULT NULL,\n" +
                "  PRIMARY KEY (`ID`)\n" +
                ")");
    }
    private void criarTabelaParametros(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS `parametros` (\n" +
                "  `ID` int(1) NOT NULL DEFAULT '0',\n" +
                "  `VIBRAR` int(1) DEFAULT NULL,\n" +
                "  `SONS` int(1) DEFAULT NULL,\n" +
                "  PRIMARY KEY (`ID`)\n" +
                ")");
    }
    private void criarTabelaQuantidadePerguntas(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS `quantidadePerguntas` (\n" +
                "  `ID` int(1) NOT NULL DEFAULT '0',\n" +
                "  `QUANTIDADE` int(3) DEFAULT NULL,\n" +
                "  PRIMARY KEY (`ID`)\n" +
                ")");
    }
    private void criarTabelaPartida(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS `partida` (\n" +
                "  `CODPARTIDA` int(11) NOT NULL DEFAULT '0',\n" +
                "  `CODUSUARIO` int(11) DEFAULT NULL,\n" +
                "  `PONTUACAO` int(11) DEFAULT NULL,\n" +
                "  `NIVEL` int(11) DEFAULT NULL,\n" +
                "  `CONCLUIDA` int(1) DEFAULT NULL,\n" +
                "  `CAPITAL` int(1) DEFAULT NULL,\n" +
                "  `BANDEIRA` int(1) DEFAULT NULL,\n" +
                "  `IDIOMA` int(1) DEFAULT NULL,\n" +
                "  `POPULACAO` int(1) DEFAULT NULL,\n" +
                "  `AREA` int(1) DEFAULT NULL,\n" +
                "  `CONTINENTE` int(1) DEFAULT NULL,\n" +
                "  `MOEDA` int(1) DEFAULT NULL,\n" +
                "  `QUANTIDADE_PERGUNTAS_RESPONDIDAS` int(3) DEFAULT NULL,\n" +
                "  `QUANTIDADE_PERGUNTAS_TOTAL` int(3) DEFAULT NULL,\n" +
                "  `VITORIA` int(1) DEFAULT NULL,\n" +
                "  `VIDAS_RESTANTES` int(1) DEFAULT NULL,\n" +
                "  `AJUDAS_RESTANTES` int(1) DEFAULT NULL,\n" +
                "  `EMBLEMA` int(1) DEFAULT NULL,\n" +
                "  `MAPA` int(1) DEFAULT NULL,\n" +
                "  `CIDADES` int(1) DEFAULT NULL,\n" +
                "  PRIMARY KEY (`CODPARTIDA`)\n" +
                ")");
    }
    private void criarTabelaPartidaTemporaria(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS `partida_temporaria` (\n" +
                "  `CODPARTIDA` int(11) NOT NULL DEFAULT '0',\n" +
                "  `PONTUACAO` int(11) DEFAULT NULL,\n" +
                "  `CAPITAL` int(1) DEFAULT NULL,\n" +
                "  `BANDEIRA` int(1) DEFAULT NULL,\n" +
                "  `IDIOMA` int(1) DEFAULT NULL,\n" +
                "  `POPULACAO` int(1) DEFAULT NULL,\n" +
                "  `AREA` int(1) DEFAULT NULL,\n" +
                "  `CONTINENTE` int(1) DEFAULT NULL,\n" +
                "  `MOEDA` int(1) DEFAULT NULL,\n" +
                "  `EMBLEMA` int(1) DEFAULT NULL,\n" +
                "  `QUANTIDADE_PERGUNTAS_RESPONDIDAS` int(3) DEFAULT NULL,\n" +
                "  `QUANTIDADE_PERGUNTAS_TOTAL` int(3) DEFAULT NULL,\n" +
                "  `VIDAS_RESTANTES` int(1) DEFAULT NULL,\n" +
                "  `AJUDAS_RESTANTES` int(1) DEFAULT NULL,\n" +
                "  PRIMARY KEY (`CODPARTIDA`)\n" +
                ")");
    }
    private void criarTabelaPais(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS `pais` (\n" +
                "  codPais int(3) NOT NULL DEFAULT '0',\n" +
                "  nomePais varchar(100) DEFAULT NULL,\n" +
                "  idioma varchar(100) DEFAULT NULL,\n" +
                "  populacao long(10) DEFAULT NULL,\n" +
                "  area varchar(12) DEFAULT NULL,\n" +
                "  continente varchar(30) DEFAULT NULL,\n" +
                "  regiao varchar(30) DEFAULT NULL,\n" +
                "  capital varchar(100) DEFAULT NULL,\n" +
                "  maiorCidade varchar(100) DEFAULT NULL,\n" +
                "  moeda varchar(100) DEFAULT NULL,\n" +
                "  namePaisEnglish varchar(100) DEFAULT NULL,\n" +
                "  pronomeD varchar(100) DEFAULT NULL,\n" +
                "  pronomeN varchar(100) DEFAULT NULL,\n" +
                "  PRIMARY KEY (`codPais`)\n" +
                ")");
    }
    private void criarTabelaCidades(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS `cidades` (\n" +
                "  ID int(4) NOT NULL DEFAULT '0',\n" +
                "  codPais int(3) NOT NULL DEFAULT '0',\n" +
                "  cidade varchar (100) DEFAULT NULL,\n" +
                "  PRIMARY KEY (`ID`)\n" +
                ")");
    }
    private void criarTabelaJogoPendente(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS `jogopendente` (\n" +
                "  ID int(1) NOT NULL DEFAULT '0',\n" +
                "  jogopendente int(1) NOT NULL DEFAULT '0',\n" +
                "  PRIMARY KEY (`ID`)\n" +
                ")");
    }
    private void criarTabelaAlternativas(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS `alternativas` (\n" +
                "  codAlternativa int(3) NOT NULL DEFAULT '0',\n" +
                "  alternativa varchar(100) DEFAULT NULL,\n" +
                "  tipoAlternativa int(1) DEFAULT NULL,\n" +
                "  valorMinimo int(15) DEFAULT NULL,\n" +
                "  valorMaximo int(15) DEFAULT NULL,\n" +
                "  PRIMARY KEY (`codAlternativa`)\n" +
                ")");
    }
    private void criarTabelaAlternativasDisponiveisJogoPendente(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS `alternativasDisponiveisJogoPendente` (\n" +
                "  ID INTEGER(1) NOT NULL DEFAULT '0',\n" +
                "alternativa varchar(100) DEFAULT NULL)");
    }
    private void criarTabelaPerguntaTemp(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS `perguntaTemp` (\n" +
                "  ID INTEGER(1) NOT NULL DEFAULT '0',\n" +
                "  codPartida int(11) DEFAULT NULL,\n" +
                "  codPergunta int(11) DEFAULT NULL,\n" +
                "  codCategoria int(11) DEFAULT NULL,\n" +
                "  alt1 varchar(100) DEFAULT NULL,\n" +
                "  alt2 varchar(100) DEFAULT NULL,\n" +
                "  alt3 varchar(100) DEFAULT NULL,\n" +
                "  alt4 varchar(100) DEFAULT NULL,\n" +
                "  tempoRestante int(2) DEFAULT NULL,\n" +
                "  alternativasDisponiveis int(1) DEFAULT NULL,\n" +
                "  PRIMARY KEY (`ID`)\n" +
                ")");
    }
    private void criarTabelaConquistas(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS `conquistas` (\n" +
                "  ID INTEGER(11) NOT NULL DEFAULT '0',\n" +
                "  conquista varchar(100) DEFAULT NULL,\n" +
                "  qtd_desbloqueio int(6) DEFAULT NULL,\n" +
                "  qtd_de_acertos int(8) DEFAULT NULL,\n" +
                "  desbloqueio int(1) DEFAULT NULL,\n" +
                "  categoria int(2) DEFAULT NULL,\n" +
                "  PRIMARY KEY (`ID`)\n" +
                ")");
    }
    private void criarTabelaPerguntasPartidas(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS `perguntas_partidas` (\n" +
                "  ID INTEGER(11) NOT NULL DEFAULT '0',\n" +
                "  codPartida int(11) DEFAULT NULL,\n" +
                "  codPergunta int(11) DEFAULT NULL,\n" +
                "  codCategoria int(11) DEFAULT NULL,\n" +
                "  respostaCerta int(1) DEFAULT NULL,\n" +
                "  PRIMARY KEY (`ID`)\n" +
                ")");
    }
    private void criaTabelaCategoria(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS `categorias` (\n" +
                "  codCategoria int(3) NOT NULL DEFAULT '0',\n" +
                "  categoria varchar(100) DEFAULT NULL,\n" +
                "  habilitada int(1) DEFAULT NULL,\n" +
                "  selecionada int(1) DEFAULT NULL,\n" +
                "  PRIMARY KEY (`codCategoria`)\n" +
                ")");
    }

    private void criaTabelaHabilitaQtdPerguntas(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS `habilitaQtdPerguntas` (\n" +
                "  ID int(1) NOT NULL DEFAULT '0',\n" +
                "  descricao varchar (100) DEFAULT NULL,\n" +
                "  habilitada int(1) DEFAULT NULL,\n" +
                "  qtdDesbloqueio int(2) DEFAULT NULL,\n" +
                "  PRIMARY KEY (`ID`)\n" +
                ")");
    }

    private void popularTabelaPais(SQLiteDatabase db) {
        db.execSQL("INSERT INTO pais VALUES" +


                "(1,'Afeganistão','Pachto / Dari','32225560','652090','Ásia',' Ásia central ',' Cabul ',' Cabul ','afegani','Afghanistan','do','no'), \n" +
                "(2,'África do Sul','Inglês/Africaner/Zulu/Ndebele/Xhosa/Sotho/Suázi/Venda/Tsonga/Tswana','58775022','1221037','África',' África austral ',' Pretória ',' Joanesburgo ','rand','South Africa','da','na'), \n" +
                "(3,'Albânia','Albanês','2845955','28748','Europa',' Europa meridional ',' Tirana ',' Tirana ','lek','Albania','da','na'), \n" +
                "(4,'Alemanha','Alemão','83149300','356733','Europa',' Europa central ',' Berlim ',' Berlim ','euro','Germany','da','na'), \n" +
                "(5,'Andorra','Catalão','77543','453','Europa',' Europa meridional ',' Andorra a velha ',' Andorra a velha ','Euro','Andorra','de','em'), \n" +
                "(6,'Angola','Português','31127674','1246700','África',' África austral ',' Luanda ',' Luanda ','kwanza','Angola','da','na'), \n" +
                //"(7,'Anguila','Inglês','14869','102','América',' Caribe ',' The Valley ',' The Valley ','Dólar do Caribe Oriental','Anguilla','da','na'), \n" +
                "(8,'Antígua e Barbuda','Inglês','96453','440','América',' Caribe ',' Saint Johns ',' Saint Johns ','Dólar do Caribe Oriental','Antigua and Barbuda','de','em'), \n" +
                "(9,'Arábia Saudita','Árabe','34218169','2149690','Ásia',' Oriente médio ',' Riade ',' Riade ','riyal','Saudi Arabia','da','na'), \n" +
                "(10,'Argélia','Árabe','43000000','2381741','África',' Norte da África ',' Argel ',' Argel ','dinar','Algeria','da','na'), \n" +
                "(11,'Argentina','Espanhol','44938712','2780400','América',' América do sul ',' Buenos Aires ',' Buenos Aires ','peso argentino','Argentina','da','na'), \n" +
                "(12,'Armênia','Armenio','2957500','29800','Europa',' Leste europeu ',' Erevã ',' Erevã ','dram','Armenia','da','na'), \n" +
                //"(13,'Aruba','Holandês','110108','180','América',' Caribe ',' Oranjestad ',' Oranjestad ','Florim arubano','Aruba','da','na'), \n" +
                "(14,'Austrália','Inglês','25699280','7692024','Oceania',' Australásia ',' Camberra ',' Sidney ','dólar australiano','Australia','da','na'), \n" +
                "(15,'Áustria','Alemão','8902600','83853','Europa',' Europa central ',' Viena ',' Viena ','euro','Austria','da','na'), \n" +
                "(16,'Azerbaijão','Azeri','10067108','86600','Europa',' Leste europeu ',' Baku ',' Baku ','manat','Azerbaijan','do','no'),\n" +
                "(17,'Bahamas','Inglês','385340','13878','América',' Caribe ',' Nassau ',' Nassau ','Dólar Baamiano','Bahamas','das','nas'),\n" +
                "(18,'Bangladesh','Bengali','168557578','143998','Ásia',' Ásia meridional ',' Daca ',' Daca ','taka','Bangladesh','de','em'),\n" +
                "(19,'Barbados','Inglês','287025','430','América',' Caribe ',' Bridgetown ',' Bridgetown ','dólar Barbadense','Barbados','de','em'),\n" +
                "(20,'Bahrein','Árabe','1543300','678','Ásia',' Oriente médio ',' Manama ',' Manama ','dinar bareinita','Bahrain','do','no'), \n" +
                "(21,'Bélgica','Holandês / Alemão / Francês','11524454','30519','Europa',' Europa ocidental ',' Bruxelas ',' Antuérpia ','euro','Belgium','da','na'), \n" +
                "(22,'Belize','Inglês','408487','22965','América',' América Central ',' Belmopan ',' Cidade de Belize ','dólar de Belize','Belize','de','em'), \n" +
                "(23,'Benim','Francês','11733059','112622','África',' África ocidental ',' Porto Novo ',' Cotonou ','franco','Benin','de','em'), \n" +
                "(24,'Bielorrússia','Russo  / Bielorrusso','9408400','207600','Europa',' Leste europeu ',' Minsk ',' Minsk ','rublo biélorrusso','Belarus','da','na'), \n" +
                "(25,'Bolívia','Espanhol / Quíchua / Aimará / Guaraní','11469896','1098581','América',' América do Sul ',' Sucre ',' Santa Cruz de la Sierra ','boliviano','Bolivia','da','na'), \n" +
                "(26,'Bósnia e Herzegovina','Bósnio / Sérvio / Croata','3301000','51129','Europa',' Europa meridional ',' Sarajevo ',' Sarajevo ','marco conversível','Bosnia and Herzegovina','da','na'), \n" +
                "(27,'Botsuana','Inglês / Tswana','2338851','581730','África',' África austral ',' Gaborone ',' Gaborone ','pula','Botswana','de','em'), \n" +
                "(28,'Brasil','Português','211482342','8515767','América',' América do Sul ',' Brasília ',' São Paulo ','real','Brazil','do','no'), \n" +
                "(29,'Brunei','Malaio','442400','5765','Ásia',' Sudeste asiático ',' Bandar Seri Begawan ',' Bandar Seri Begawan ','dólar de Brunei','Brunei','de','em'), \n" +
                "(30,'Bulgária','Búlgaro','6951482','110912','Europa',' Leste europeu ',' Sofia ',' Sofia ','lev','Bulgaria','da','na'), \n" +
                "(31,'Burquina Faso','Francês','20870060','274200','África',' África ocidental ',' Ouagadougou ',' Ouagadougou ','franco CFA','Burkina Faso','da','na'), \n" +
                "(32,'Burundi','Inglês / Francês /Kirundi','10953317','27834','África',' África central ',' Bujumbura ',' Bujumbura ','franco de Burundi','Burundi','do','no'), \n" +
                "(33,'Butão','Butanês','741672','47000','Ásia',' Ásia meridional ',' Thimbu ',' Thimbu ','ngultrum','Bhutan','do','no'), \n" +
                "(34,'Cabo Verde','Português','550483','4033','África',' África ocidental ',' Praia ',' Praia ','Escudo','Cape Verde','de','em'),\n" +
                "(35,'Camarões','Francês / Inglês','26545864','475442','África',' África central ',' Yaoundé ',' Douala ','franco CFA','Cameroon','de','em'),\n" +
                "(36,'Camboja','Khmer','15288489','181035','Ásia',' Sudeste asiático ',' Phnom Penh ',' Phnom Penh ','riel','Cambodia','do','no'), \n" +
                "(37,'Canadá','Inglês / Francês','38017693','9984670','América',' América do Norte ',' Ottawa ',' Toronto ','dólar canadense','Canada','do','no'), \n" +
                "(38,'Catar','Árabe','2747282','11000','Ásia',' Oriente médio ',' Doha ',' Doha ','rial','Qatar','do','no'), \n" +
                "(39,'Cazaquistão','Cazaque','18694800','2724900','Ásia',' Ásia central ',' Nur-Sultã ',' Almaty ','tenge','Kazakhstan','do','no'), \n" +
                "(40,'Chade','Francês','16244513','1284000','África',' África central ',' NDjamena ',' NDjamena ','franco CFA','Chad','do','no'), \n" +
                "(41,'Chile','Espanhol','19107216','756945','América',' América do Sul ',' Santiago ',' Santiago ','peso chileno','Chile','do','no'), \n" +
                "(42,'China','Mandarim','1402509320','9596961','Ásia',' Ásia oriental ',' Pequim ',' Xangai ','yuan','China','da','na'), \n" +
                "(43,'Chipre','Grego / Turco','875900','9251','Europa',' Europa meridional ',' Nicósia ',' Nicósia ','euro','Cyprus','do','no'), \n" +
                "(44,'Colômbia','Espanhol','49395678','1138914','América',' América do Sul ',' Bogotá ',' Bogotá ','peso colombiano','Colombia','da','na'), \n" +
                "(45,'Comores','Árabe / Francês','873724','2235','África',' África oriental ',' Moroni ',' Moroni ','Franco Comoriano','Comoros','de','em'), \n" +
                "(46,'Coreia do Norte','Coreano','25450000','120538','Ásia',' Ásia oriental ',' Pyongyang ',' Pyongyang ','won norte coreano','North Korea','da','na'), \n" +
                "(47,'Coreia do Sul','Coreano','51780579','99016','Ásia',' Ásia oriental ',' Seul ',' Seul ','won','South Korea','da','na'), \n" +
                "(48,'Costa do Marfim','Francês','25823071','322463','África',' África ocidental ',' Abidjã ',' Abidjã ','franco CFA','Ivory Coast','da','na'), \n" +
                "(49,'Costa Rica','Espanhol','5058007','51100','América',' América Central ',' San José ',' San José ','colón costa riquenho','Costa Rica','da','na'),\n" +
                "(50,'Croácia','Croata','4076246','56538','Europa',' Europa meridional ',' Zagreb ',' Zagreb ','kuna croata','Croatia','da','na'), \n" +
                "(51,'Cuba','Espanhol','11209628','110861','América',' Caribe ',' Havana ',' Havana ','peso','Cuba','de','em'), \n" +
                "(52,'Dinamarca','Dinamarquês','5822763','43077','Europa',' Europa Setentrional ',' Copenhague ',' Copenhague ','coroa','Denmark','da','na'), \n" +
                "(53,'Djibuti','Árabe / Francês / Somali','1078373','23200','África',' África oriental ',' Djbuti ',' Djbuti ','franco djboutiano','Djibouti','do','no'), \n" +
                "(54,'Domínica','Inglês','71808','751','América',' Caribe ',' Roseau ',' Roseau ','Dólar do Caribe Oriental','Dominica','de','em'), \n" +
                "(55,'Egito','Árabe','100336647','1001049','África',' Norte da África ',' Cairo ',' Cairo ','libra egípcia','Egypt','do','no'), \n" +
                "(56,'El Salvador','Espanhol','6486201','21041','América',' América Central ',' San Salvador ',' San Salvador ','dólar americano','El Salvador','de','em'), \n" +
                "(57,'Emirados Árabes Unidos','Árabe','9890400','83600','Ásia',' Oriente médio ',' Abu Dhabi ',' Dubai ','dirham dos emirados','United Arab Emirates','dos','nos'), \n" +
                "(58,'Equador','Espanhol','17479032','283561','América',' América do Sul ',' Quito ',' Guayaquil ','dólar americano','Ecuador','do','no'), \n" +
                "(59,'Eritreia','Árabe / Tigrínio','3497117','117600','África',' África oriental ',' Asmara ',' Asmara ','nakfa','Eritrea','da','na'), \n" +
                "(60,'Eslováquia','Eslovaco','5457873','49035','Europa',' Europa central ',' Bratislava ',' Bratislava ','euro','Slovakia','da','na'), \n" +
                "(61,'Eslovênia','Esloveno','2094060','20251','Europa',' Europa meridional ',' Liubliana ',' Liubliana ','euro','Slovenia','da','na'),\n" +
                "(62,'Espanha','Espanhol','47100396','504782','Europa',' Europa meridional ',' Madrid ',' Madrid ','euro','Spain','da','na'), \n" +
                "(63,'Estados Unidos','Inglês','329634908','9371174','América',' América do Norte ',' Washington ',' Nova Iorque ','dólar americano','United States','dos','nos'), \n" +
                "(64,'Estônia','Estoniano','1328360','45100','Europa',' Europa Setentrional ',' Talin ',' Talin ','euro','Estonia','da','na'), \n" +
                "(65,'Etiópia','Amárico','98665000','1104300','África',' África oriental ',' Adis Abeba ',' Adis Abeba ','birr etíope','Ethiopia','da','na'), \n" +
                "(66,'Fiji','Inglês / Fijiano / Hindi','884887','18274','Oceania',' Melanésia ',' Suva ',' Suva ','Dólar de fiji','Fiji','de','em'), \n" +
                "(67,'Filipinas','Inglês / Filipino','108584658','300000','Ásia',' Sudeste asiático ',' Manila ',' Cidade Quezon ','peso filipino','Philippines','das','nas'), \n" +
                "(68,'Finlândia','Finlandês / Sueco','5528390','338145','Europa',' Europa Setentrional ',' Helsinque ',' Helsinque ','euro','Finland','da','na'), \n" +
                "(69,'França','Francês','67075000','551500','Europa',' Europa ocidental ',' Paris ',' Paris ','euro','France','da','na'), \n" +
                "(70,'Gabão','Francês','2172579','267667','África',' África central ',' Libreville ',' Libreville ','franco CFA','Gabon','do','no'), \n" +
                "(71,'Gâmbia','Inglês','2347706','11295','África',' África ocidental ',' Banjul ',' Serekunda ','dalasi','Gambia','de','em'), \n" +
                "(72,'Gana','Inglês','30280811','238533','África',' África ocidental ',' Acra ',' Acra ','cedi','Ghana','de','em'), \n" +
                "(73,'Geórgia','Georgiano','3723464','69700','Europa',' Leste europeu ',' Tbilisi ',' Tbilisi ','lari','Georgia','da','na'),\n" +
                //"(74,'Gibraltar','Inglês','33691','6.5','Europa',' Europa meridional ',' Gibraltar ',' Gibraltar ','Libra de Gibraltar','Gibraltar','de','em'), \n" +
                "(75,'Granada','Inglês','112003','344','América',' Caribe ',' Saint Georges ',' Saint Georges ','Dólar do Caribe Oriental','Grenada','de','em'), \n" +
                "(76,'Grécia','Grego','10724599','131990','Europa',' Europa meridional ',' Atenas ',' Atenas ','euro','Greece','da','na'), \n" +
                "(77,'Guiana','Inglês','782766','214969','América',' América do Sul ',' Georgetown ',' Georgetown ','dólar guianense','Guyana','da','na'), \n" +
                "(78,'Guiné','Francês','12218357','245857','África',' África ocidental ',' Conacri ',' Conacri ','franco guineense','Guinea','da','na'), \n" +
                "(79,'Guiné Bissau','Português','1604528','36125','África',' África ocidental ',' Bissau ',' Bissau ','franco CFA','Guinea/Bissau','da','na'), \n" +
                "(80,'Guiné Equatorial','Espanhol / Francês / Português','1358276','28051','África',' África central ',' Malabo ',' Bata ','franco CFA','Equatorial Guinea','da','na'), \n" +
                "(81,'Haiti','Francês / Crioulo Haitiano','11577779','27750','América',' Caribe ',' Porto Príncipe ',' Porto Príncipe ','gourde','Haiti','do','no'), \n" +
                "(82,'Honduras','Espanhol','9158345','112088','América',' América Central ',' Tegucigalpa ',' Tegucigalpa ','lempira','Honduras','de','em'), \n" +
                "(83,'Hungria','Húngaro','9772756','93032','Europa',' Europa central ',' Budapeste ',' Budapeste ','florim húngaro','Hungary','da','na'),\n" +
                "(84,'Iêmen','Árabe','29825968','527968','Ásia',' Oriente médio ',' Sana ',' Sana ','rial iemenita','Yemen','do','no'), \n" +
                "(85,'Ilhas Marshall','Inglês / Marshalês','55500','181','Oceania',' Micronésia ',' Majuro ',' Majuro ','dólar americano','Marshall Islands','das','nas'), \n" +
                "(86,'Ilhas Salomão','Inglês','680806','28896','Oceania',' Melanésia ',' Honiara ',' Honiara ','Dólar de Salomão','Solomon Islands','das','nas'), \n" +
                "(87,'Índia','Inglês / Hindi','1361865555','3287590','Ásia',' Ásia meridional ',' Deli ',' Mumbai ','rupia indiana','India','da','na'), \n" +
                "(88,'Indonésia','Indonésio','266911900','1904569','Ásia',' Sudeste asiático ',' Jacarta ',' Jacarta ','rupia indonésia','Indonesia','da','na'), \n" +
                "(89,'Irã','Persa','83421094','1628750','Ásia',' Oriente médio ',' Teerã ',' Teerã ','rial','Iran','do','no'), \n" +
                "(90,'Iraque','Árabe / Curdo','39127900','438317','Ásia',' Oriente médio ',' Bagdá ',' Bagdá ','dinar iraquiano','Iraq','do','no'), \n" +
                "(91,'Irlanda','Inglês / Irlandês','4921500','70273','Europa',' Europa Setentrional ',' Dublin ',' Dublin ','euro','Ireland','da','na'), \n" +
                "(92,'Islândia','Islandês','366130','103000','Europa',' Europa Setentrional ',' Reykjavík ',' Reykjavík ','coroa islandesa','Iceland','da','na'), \n" +
                "(93,'Israel','Hebraico','9192125','20770','Ásia',' Oriente médio ',' Jerusalem ',' Jerusalem ','novo shekel','Israel','de','em'), \n" +
                "(94,'Itália','Italiano','60238522','301338','Europa',' Europa meridional ',' Roma ',' Roma ','euro','Italy','da','na'), \n" +
                "(95,'Jamaica','Inglês','2726667','10991','América',' Caribe ',' Kingston ',' Kingston ','dólar jamaicano','Jamaica','da','na'), \n" +
                "(96,'Japão','Japonês','125950000','377975','Ásia',' Ásia oriental ',' Tóquio ',' Tóquio ','iene','Japan','do','no'), \n" +
                "(97,'Jordânia','Árabe','10673288','89342','Ásia',' Oriente médio ',' Amã ',' Amã ','dinar jordano','Jordan','da','na'), \n" +
                "(98,'Kosovo','Albanês / Sérvio','1795666','10887','Europa',' Europa meridional ',' Pristina ',' Pristina ','Euro','Kosovo','do','no'), \n" +
                "(99,'Kuwait','Árabe','4420110','17818','Ásia',' Oriente médio ',' Cidade do Kuwait ',' Cidade do Kuwait ','dinar kwaitianio','Kuwait','do','no'), \n" +
                "(100,'Laos','Laosiano','7123205','236800','Ásia',' Sudeste asiático ',' Vientiane ',' Vientiane ','kip','Laos','de','em'), \n" +
                "(101,'Lesoto','Sesoto / Inglês','2007201','30355','África',' África austral ',' Maseru ',' Maseru ','loti de Lesoto','Lesotho','do','no'), \n" +
                "(102,'Letônia','Letão','1906800','64559','Europa',' Europa Setentrional ',' Riga ',' Riga ','euro','Latvia','da','na'), \n" +
                "(103,'Líbano','Árabe','6825442','10452','Ásia',' Oriente médio ',' Beirute ',' Beirute ','libra libanesa','Lebanon','do','no'),\n" +
                "(104,'Libéria','Inglês','4475353','111369','África',' África ocidental ',' Monróvia ',' Monróvia ','dólar liberiano','Liberia','da','na'), \n" +
                "(105,'Líbia','Árabe','6871287','1759540','África',' Norte da África ',' Trípoli ',' Trípoli ','dinar líbio','Libya','da','na'), \n" +
                "(106,'Liechtenstein','Alemão','38749','160','Europa',' Europa ocidental ',' Vaduz ',' Schaan ','Franco Suiço','Liechtenstein','de','em'), \n" +
                "(107,'Lituânia','Lituano','2793471','65300','Europa',' Europa Setentrional ',' Vilnius ',' Vilnius ','euro','Lithuania','da','na'), \n" +
                "(108,'Luxemburgo','Alemão / Francês / Luxermburguês','626108','2586','Europa',' Europa ocidental ',' Luxemburgo ',' Luxemburgo ','euro','Luxembourg','de','em'), \n" +
                "(109,'Macedônia ','Albanês / Macedônio','2077132','25713','Europa',' Europa meridional ',' Escópia ',' Escópia ','dinar macedônio','Macedonia','da','na'), \n" +
                "(110,'Madagascar','Malgaxe / Francês','26251309','587041','África',' África austral ',' Antananarivo ',' Antananarivo ','ariary','Madagascar','de','em'), \n" +
                "(111,'Malásia','Malaio / Inglês','32770760','330803','Ásia',' Sudeste asiático ',' Kuala Lumpur ',' Kuala Lumpur ','ringgit','Malaysia','da','na'), \n" +
                "(112,'Malawi','Inglês','19129952','118484','África',' África austral ',' Lilongwe ',' Lilongwe ','kwacha malawiana','Malawi','do','no'), \n" +
                "(113,'Maldivas','Divehi','374775','298','Ásia',' Ásia meridional ',' Malé ',' Malé ','Rupia Maldivia','Maldives','das','nas'), \n" +
                "(114,'Mali','Francês','20250833','1240192','África',' África ocidental ',' Bamako ',' Bamako ','franco CFA','Mali','do','no'), \n" +
                "(115,'Malta','Maltês / Inglês','493559','316','Europa',' Europa meridional ',' Valeta ',' Birkirkara ','euro','Malta','de','em'), \n" +
                "(116,'Marrocos','Árabe','35888525','446550','África',' Norte da África ',' Rabat ',' Casablanca ','dirham marroquino','Morocco','do','no'), \n" +
                "(117,'Maurício','Inglês','1265577','2040','África',' África oriental ',' Port Louis ',' Port Louis ','Rupia Mauriciana','Mauritius','de','em'), \n" +
                "(118,'Mauritânia','Árabe','4077347','1025520','África',' África ocidental ',' Nouakchott ',' Nouakchott ','ouguiya','Mauritania','da','na'), \n" +
                "(119,'México','Espanhol','126577691','1964375','América',' América do Norte ',' Cidade do México ',' Cidade do México ','peso mexicano','Mexico','do','no'), \n" +
                "(120,'Micronésia','Inglês','104468','702','Oceania',' Micronésia ',' Palikir ',' Kolonia ','Dólar Americano','Micronesia','da','na'), \n" +
                "(121,'Moçambique','Português','30066648','801590','África',' África austral ',' Maputo ',' Maputo ','metical','Mozambique','de','em'),\n" +
                "(122,'Moldávia','Romeno','2681735','33851','Europa',' Leste europeu ',' Chisinau ',' Chisinau ','leu moldávio','Moldova','da','na'), \n" +
                "(123,'Mônaco','Francês','38100','2','Europa',' Europa meridional ',' Monaco Ville ',' Monaco Ville ','Euro ','Monaco','de','em'), \n" +
                "(124,'Mongólia','Mongol','3315999','1564116','Ásia',' Ásia oriental ',' Ulan Bator ',' Ulan Bator ','togrog','Mongolia','da','na'), \n" +
                "(125,'Montenegro','Montenegrino','622359','13812','Europa',' Europa meridional ',' Podgorica ',' Podgorica ','euro','Montenegro','de','em'), \n" +
                "(126,'Myanmar','Birmanês','54339766','676578','Ásia',' Sudeste asiático ',' Naypyidaw ',' Rangum ','quiat','Myanmar','de','em'), \n" +
                "(127,'Namíbia','Inglês','2458936','824116','África',' África austral ',' Windhoek ',' Windhoek ','dólar namibiano','Namibia','da','na'), \n" +
                "(128,'Nauru','Nauruano','11000','21','Oceania',' Micronésia ',' Yaren ',' Yaren ','Dólar Australiano','Nauru','de','em'),\n" +
                "(129,'Nepal','Nepalês','29996478','147181','Ásia',' Ásia meridional ',' Catmandu ',' Catmandu ','rúpia nepalesa','Nepal','do','no'), \n" +
                "(130,'Nicarágua','Espanhol','6460411','131812','América',' América Central ',' Manágua ',' Manágua ','córdoba','Nicaragua','da','na'), \n" +
                "(131,'Níger','Francês','22314743','1267000','África',' África ocidental ',' Niamey ',' Niamey ','franco CFA','Niger','do','no'), \n" +
                "(132,'Nigéria','Inglês','206139587','923768','África',' África ocidental ',' Abuja ',' Lagos ','naira','Nigeria','da','na'), \n" +
                "(133,'Noruega','Norueguês','5367580','385155','Europa',' Europa Setentrional ',' Oslo ',' Oslo ','coroa norueguesa','Norway','da','na'), \n" +
                "(134,'Nova Zelândia','Inglês / Maori','4983332','270534','Oceania',' Australásia ',' Welington ',' Auckland ','dólar da Nova Zelândia','New Zealand','da','na'), \n" +
                "(135,'Omã','Árabe','4664599','309500','Ásia',' Oriente médio ',' Mascate ',' Mascate ','rial','Oman','do','no'), \n" +
                "(136,'Países Baixos','Holandês','17462581','41528','Europa',' Europa ocidental ',' Amsterdã ',' Amsterdã ','euro','Netherlands','dos','nos'), \n" +
                "(137,'Palau','Inglês / Palauan','17900','459','Oceania',' Micronésia ',' Ngerulmud ',' Koror ','Dólar americano','Palau','de','em'), \n" +
                "(138,'Palestina','Árabe','4976684','6020','Ásia',' Oriente médio ',' Ramallah ',' Gaza ','Novo shekel','Palestine','da','na'), \n" +
                "(139,'Panamá','Espanhol','4218808','75517','América',' América Central ',' Cidade do Panamá ',' Cidade do Panamá ','balboa panamenha','Panama','do','no'), \n" +
                "(140,'Papua-Nova Guiné','Inglês / Tok Pisin / Hiri Motu','8935000','462840','Oceania',' Australásia ',' Port Moresby ',' Port Moresby ','kina','Papua New Guinea','da','na'), \n" +
                "(141,'Paquistão','Urdu','220892311','880254','Ásia',' Ásia meridional ',' Islamabad ',' Carachi ','rúpia','Pakistan','do','no'), \n" +
                "(142,'Paraguai','Espanhol / Guarani','7252672','406752','América',' América do Sul ',' Assunção ',' Assunção ','guarani','Paraguay','do','no'), \n" +
                "(143,'Peru','Espanhol / Quíchua','32131400','1285216','América',' América do Sul ',' Lima ',' Lima ','nuevo sol','Peru','do','no'), \n" +
                //"(144,'Polinésia Francesa','Francês','275918','4000','Oceania',' Polinésia ',' Papeete ',' Faaa ','Franco CFP','French Polynesia','da','na'), \n" +
                "(145,'Polônia','Polonês','38379000','312685','Europa',' Europa central ',' Varsóvia ',' Varsóvia ','zloty','Poland','da','na'), \n" +
                "(146,'Portugal','Português','10276617','92152','Europa',' Europa meridional ',' Lisboa ',' Lisboa ','euro','Portugal','de','em'), \n" +
                "(147,'Quênia','Inglês','47564296','580367','África',' África oriental ',' Nairóbi ',' Nairóbi ','xelim queniano','Kenya','do','no'), \n" +
                "(148,'Quirguizistão','Quirguiz / Russo','6533500','199951','Ásia',' Ásia central ',' Bisqueque ',' Bisqueque ','som','Kyrgyzstan','do','no'), \n" +
                "(149,'Quiribati','Inglês','120100','726','Oceania',' Micronésia ',' Tarawa do Sul ',' Tarawa ','Dólar de Kiribati','Kiribati','de','em'),\n" +
                "(150,'Reino Unido','Inglês','66435550','242514','Europa',' Europa Setentrional ',' Londres ',' Londres ','libra esterlina','United Kingdom','do','no'), \n" +
                "(151,'República Centro Africana','Francês / Sango','5496011','622984','África',' África central ',' Bangui ',' Bangui ','franco CFA','Central African Republic','da','na'), \n" +
                "(152,'República Democrática do Congo','Francês','89561404','2344858','África',' África central ',' Kinshasa ',' Kinshasa ','franco congolês','Congo/Kinshasa','da','na'), \n" +
                "(153,'República do Congo','Francês','5518092','342000','África',' África central ',' Brazzaville ',' Brazzaville ','franco centro africano','Congo/Brazzaville','da','na'), \n" +
                "(154,'República Dominicana','Espanhol','10358320','48734','América',' Caribe ',' Santo Domingo ',' Santo Domingo ','peso','Dominican Republic','da','na'), \n" +
                "(155,'República Tcheca','Tcheco','10693939','78867','Europa',' Europa central ',' Praga ',' Praga ','coroa','Czech Republic','da','na'), \n" +
                "(156,'Romênia','Romeno','19405156','238391','Europa',' Leste europeu ',' Bucareste ',' Bucareste ','leu romeno','Romania','da','na'), \n" +
                "(157,'Ruanda','Francês / Inglês / Suaíli / Quiniaruanda','12374397','26338','África',' África central ',' Kigali ',' Kigali ','franco ruandês','Rwanda','da','na'), \n" +
                "(158,'Rússia','Russo','146745098','17098246','Europa',' Leste europeu ',' Moscou ',' Moscou ','rublo','Russia','da','na'), \n" +
                "(159,'Samoa','Samoano / Inglês','200874','2831','Oceania',' Polinésia ',' Apia ',' Apia ','tala','Samoa','da','na'), \n" +
                "(160,'San Marino','Italiano','33533','61','Europa',' Europa meridional ',' San Marino ',' Dogana ','euro','San Marino','de','em'), \n" +
                "(161,'Santa Lúcia','Inglês','178696','539','América',' Caribe ',' Castries ',' Castries ','Dólar do Caribe','Saint Lucia','de','em'), \n" +
                "(162,'São Cristóvão e Neves','Inglês','52823','261','América',' Caribe ',' Brasseferre ',' Brasseferre ','Dólar do Caribe','Saint Kitts and Nevis','de','em'), \n" +
                "(163,'São Tomé e Príncipe','Português','201784','1001','África',' África ocidental ',' São Tomé ',' Água Grande ','Dobra','São Tomé and Príncipe','de','em'), \n" +
                "(164,'São Vicente e Granadinas','Inglês','110608','388','América',' Caribe ',' Kingstown ',' Kingstown ','Dólar do Caribe','Saint Vincent and the Grenadines','de','em'), \n" +
                "(165,'Seycheles','Inglês / Francês / Crioulo','97625','455','África',' África oriental ',' Victoria ',' Victoria ','Rupia ','Seychelles','de','em'), \n" +
                "(166,'Senegal','Francês','16209125','196722','África',' África ocidental ',' Dakar ',' Dakar ','franco CFA','Senegal','do','no'), \n" +
                "(167,'Serra Leoa','Inglês','7901454','71740','África',' África ocidental ',' Freetown ',' Freetown ','leone','Sierra Leone','de','em'), \n" +
                "(168,'Sérvia','Sérvio','6933764','88361','Europa',' Europa meridional ',' Belgrado ',' Belgrado ','dinar sérvio','Serbia','da','na'), \n" +
                "(169,'Singapura','Malaio / Tamil / Inglês / Mandarim','5703600','618','Ásia',' Sudeste asiático ',' Singapura ',' Singapura ','dólar de Singapura','Singapore','de','em'), \n" +
                "(170,'Síria','Árabe','17500657','185180','Ásia',' Oriente médio ',' Damasco ',' Alepo ','libra síria','Syria','da','na'), \n" +
                "(171,'Somália','Árabe / Somali','15893219','637657','África',' África oriental ',' Mogadíscio ',' Mogadíscio ','xelim somali','Somalia','da','na'),\n" +
                "(172,'Sri Lanka','Cingalês / Tâmil','21803000','65610','Ásia',' Ásia meridional ',' Kotte ',' Colombo ','rúpia ceilandesa','Sri Lanka','do','no'), \n" +
                "(173,'Essuatíni','Inglês / Suázi','1093238','17364','África',' África austral ',' Mbabane ',' Manzini ','lilangeni suazi','Swaziland','de','em'), \n" +
                "(174,'Sudão','Árabe / Inglês','42480095','1886068','África',' Norte da África ',' Cartum ',' Omdurman ','dinar sudanês','Sudan','do','no'), \n" +
                "(175,'Sudão do Sul','Inglês / Suaíli','12778250','644329','África',' Norte da África ',' Juba ',' Juba ','libra sul sudanesa','South Sudan','do','no'), \n" +
                "(176,'Suécia','Sueco','10338368','450295','Europa',' Europa Setentrional ',' Estocolmo ',' Estocolmo ','coroa sueca','Sweden','da','na'), \n" +
                "(177,'Suíça','Alemão / Francês / Italiano / Romanche','8603899','41285','Europa',' Europa ocidental ',' Berna ',' Zurique ','franco suíço','Switzerland','da','na'), \n" +
                "(178,'Suriname','Holandês','581372','163820','América',' América do Sul ',' Paramaribo ',' Paramaribo ','dólar do suriname','Suriname','do','no'), \n" +
                "(179,'Tailândia','Tailandês','66501392','513120','Ásia',' Sudeste asiático ',' Bangcoc ',' Bangcoc ','baht tailandês','Thailand','da','na'), \n" +
                //"(180,'Taiwan','Mandarim','23604265','36179','Ásia',' Ásia oriental ',' Taipé ',' Nova Taipé ','novo dólar taiwanês','Taiwan','do','no'), \n" +
                "(181,'Tajiquistão','Tajique','9127000','143100','Ásia',' Ásia central ',' Duchambe ',' Duchambe ','somoni','Tajikistan','do','no'), \n" +
                "(182,'Tanzânia','Suaíli','55890747','945087','África',' África oriental ',' Dodoma ',' Dar es Salaam ','xelim tanzaniano','Tanzania','da','na'), \n" +
                "(183,'Timor Leste','Português / Tétum','1387149','14874','Ásia',' Sudeste asiático ',' Dili ',' Dili ','Dólar Americano','Timor/Leste','do','no'), \n" +
                "(184,'Togo','Francês','7538000','56785','África',' África ocidental ',' Lomé ',' Lomé ','franco CFA','Togo','do','no'), \n" +
                "(185,'Tonga','Inglês / Tonganês','100651','747','Oceania',' Polinésia ',' Nukualofa ',' Nukualofa ','Paanga','Tonga','de','em'), \n" +
                "(186,'Trindade e Tobago','Inglês / Espanhol','1363985','5130','América',' Caribe ',' Port of Spain ',' Chaguanas ','dolar de trindade e tobago','Trinidad and Tobago','de','em'), \n" +
                "(187,'Tunísia','Árabe','11722038','163610','África',' Norte da África ',' Túnis ',' Túnis ','dinar tunisiano','Tunisia','da','na'), \n" +
                "(188,'Turcomenistão','Turcomeno','6031187','488100','Ásia',' Ásia central ',' Asgabate ',' Asgabate ','manate turcomeno','Turkmenistan','do','no'), \n" +
                "(189,'Turquia','Turco','83154997','783562','Europa',' Oriente médio ',' Ancara ',' Istambul ','lira turca','Turkey','da','na'), \n" +
                "(190,'Tuvalu','Inglês / Tuvaluano','10200','26','Oceania',' Polinésia ',' Funafuti ',' Alapi ','dólar de Tuvalu','Tuvalu','do','no'), \n" +
                "(191,'Ucrânia','Ucraniano','41858119','576604','Europa',' Leste europeu ',' Kiev ',' Kiev ','grivnia','Ukraine','da','na'), \n" +
                "(192,'Uganda','Inglês / Suaíli','40229300','241038','África',' África oriental ',' Kampala ',' Kampala ','xelim de Uganda','Uganda','da','na'), \n" +
                "(193,'Uruguai','Espanhol','3518552','176215','América',' América do Sul ',' Montevideu ',' Montevideu ','peso uruguaio','Uruguay','do','no'), \n" +
                "(194,'Uzbequistão','Uzbeque','34165089','447400','Ásia',' Ásia central ',' Tashkent ',' Tashkent ','som uzbeque','Uzbekistan','do','no'), \n" +
                "(195,'Vanuatu','Bislama / Inglês / Francês','304500','12189','Oceania',' Melanésia ',' Port Vila ',' Port Vila ','Vatu','Vanuatu','de','em'), \n" +
                "(196,'Vaticano','Italiano / Latim','825','0.4','Europa',' Europa meridional ',' Vaticano ',' Vaticano ','euro','Vatican city','do','no'), \n" +
                "(197,'Venezuela','Espanhol','32219521','912050','América',' América do Sul ',' Caracas ',' Caracas ','bolivar venezuelano','Venezuela','da','na'), \n" +
                "(198,'Vietnã','Vietnamita','96208984','331212','Ásia',' Sudeste asiático ',' Hanói ',' Cidade de Ho Chi Minh ','dong','Vietnam','do','no'), \n" +
                "(199,'Zâmbia','Inglês','17885422','752612','África',' África austral ',' Lusaka ',' Lusaka ','kwacha zambiana','Zambia','da','na'), \n" +
                "(200,'Guatemala','Espanhol','17263239','108890','América',' América Central ',' Cidade da Guatemala ',' Cidade da Guatemala ','Quetzal','Guatemala','da','na'), \n" +
                "(201,'Zimbabué','Inglês','15159624','390757','África',' África austral ',' Harare ',' Harare ','dólar zimbabuano','Zimbabwe','do','no') \n");

    }
    private void popularTabelaCidades1(SQLiteDatabase db) {
        db.execSQL("INSERT INTO cidades VALUES" +
                "(1,1,'Candaar'),\n" +
                "(2,1,'Herat'),\n" +
                "(3,2,'Joanesburgo'),\n" +
                "(4,2,'Cidade do Cabo'),\n" +
                "(5,2,'Durban'),\n" +
                "(6,2,'Germinston'),\n" +
                "(7,2,'Bloemfontein'),\n" +
                "(8,3,'Durrës'),\n" +
                "(9,3,'Vlorë'),\n" +
                "(10,3,'Escodra'),\n" +
                "(11,4,'Hamburgo'),\n" +
                "(12,4,'Munique'),\n" +
                "(13,4,'Colônia'),\n" +
                "(14,4,'Frankfurt'),\n" +
                "(15,4,'Düsseldorf'),\n" +
                "(16,4,'Leipzig'),\n" +
                "(17,4,'Dortmund'),\n" +
                "(18,4,'Dresden'),\n" +
                "(19,4,'Nuremberg'),\n" +
                "(20,4,'Bonn'),\n" +
                "(21,5,'Escaldes-Engordany'),\n" +
                "(22,6,'Huambo'),\n" +
                "(23,6,'Lobito'),\n" +
                "(24,6,'Benguela'),\n" +
                "(25,8,'Codrington'),\n" +
                "(26,9,'Gidá'),\n" +
                "(27,9,'Meca'),\n" +
                "(28,9,'Medina'),\n" +
                "(29,9,'Hofufe'),\n" +
                "(30,10,'Orã'),\n" +
                "(31,10,'Constantina'),\n" +
                "(32,10,'Annaba'),\n" +
                "(33,11,'Cordoba'),\n" +
                "(34,11,'Rosário'),\n" +
                "(35,11,'Mendoza'),\n" +
                "(36,11,'San Miguel de Tucumán'),\n" +
                "(37,11,'La Plata'),\n" +
                "(38,11,'Mar del Plata'),\n" +
                "(39,11,'Salta'),\n" +
                "(40,11,'Santa Fé'),\n" +
                "(41,12,'Guiumri'),\n" +
                "(42,12,'Vanadzor'),\n" +
                "(43,14,'Sydney'),\n" +
                "(44,14,'Melbourne'),\n" +
                "(45,14,'Brisbane'),\n" +
                "(46,14,'Perth'),\n" +
                "(47,14,'Adelaide'),\n" +
                "(48,14,'Gold Coast'),\n" +
                "(49,14,'Newcastle'),\n" +
                "(50,14,'Wollongong'),\n" +
                "(51,14,'Sunshine Coast'),\n" +
                "(52,15,'Graz'),\n" +
                "(53,15,'Linz'),\n" +
                "(54,15,'Salzburg'),\n" +
                "(55,15,'Innsbruck'),\n" +
                "(56,16,'Sumqayit'),\n" +
                "(57,18,'Chittagong'),\n" +
                "(58,20,'Muarraque'),\n" +
                "(59,20,'Cidade de Hamade'),\n" +
                "(60,21,'Antuérpia'),\n" +
                "(61,21,'Gante'),\n" +
                "(62,21,'Charleroi'),\n" +
                "(63,21,'Liège'),\n" +
                "(64,21,'Schaerbeek'),\n" +
                "(65,21,'Brugge'),\n" +
                "(66,21,'Anderlecht'),\n" +
                "(67,23,'Cotonou'),\n" +
                "(68,23,'Abomei-Calavi'),\n" +
                "(69,24,'Gomel'),\n" +
                "(70,24,'Mahilou'),\n" +
                "(71,24,'Vitsyebsk'),\n" +
                "(72,24,'Brest'),\n" +
                "(73,25,'Santa Cruz de la Sierra'),\n" +
                "(74,25,'El Alto'),\n" +
                "(75,25,'Cochabamba'),\n" +
                "(76,25,'Oruro'),\n" +
                "(77,26,'Banja Luka'),\n" +
                "(78,26,'Tuzla'),\n" +
                "(79,26,'Zenica'),\n" +
                "(80,27,'Francistown'),\n" +
                "(81,28,'São Paulo'),\n" +
                "(82,28,'Rio de Janeiro'),\n" +
                "(83,28,'Belo Horizonte'),\n" +
                "(84,28,'Recife'),\n" +
                "(85,28,'Porto Alegre'),\n" +
                "(86,28,'Salvador'),\n" +
                "(87,28,'Fortaleza'),\n" +
                "(88,28,'Curitiba'),\n" +
                "(89,28,'Goiânia'),\n" +
                "(90,28,'Manaus'),\n" +
                "(91,28,'Belém'),\n" +
                "(92,28,'Campinas'),\n" +
                "(93,28,'Vitória'),\n" +
                "(94,28,'Maceió'),\n" +
                "(95,28,'Al Rayyan'),\n" +
                "(96,30,'Plovdiv'),\n" +
                "(97,30,'Varna'),\n" +
                "(98,34,'Mindelo'),\n" +
                "(99,35,'Douala'),\n" +
                "(100,35,'Garoua'),\n" +
                "(101,35,'Kousséri'),\n" +
                "(102,35,'Bamenda'),\n" +
                "(103,37,'Toronto'),\n" +
                "(104,37,'Montreal'),\n" +
                "(105,37,'Vancouver'),\n" +
                "(106,37,'Calgary'),\n" +
                "(107,37,'Edmonton'),\n" +
                "(108,37,'Quebec'),\n" +
                "(109,37,'Winnipeg'),\n" +
                "(110,37,'Hamilton'),\n" +
                "(111,37,'Kitchener'),\n" +
                "(112,39,'Almati'),\n" +
                "(113,39,'Shymkent'),\n" +
                "(114,39,'Karaganda'),\n" +
                "(115,41,'Valparaíso'),\n" +
                "(116,41,'Concepción'),\n" +
                "(117,41,'Puente Alto'),\n" +
                "(118,41,'Maipú'),\n" +
                "(119,41,'La Serena'),\n" +
                "(120,41,'Antofagasta'),\n" +
                "(121,41,'Viña del Mar'),\n" +
                "(122,42,'Xangai'),\n" +
                "(123,42,'Shenzhen'),\n" +
                "(124,42,'Shenyang'),\n" +
                "(125,42,'Dongguan'),\n" +
                "(126,42,'Tianjin'),\n" +
                "(127,42,'Wuhan'),\n" +
                "(128,42,'Nanjing'),\n" +
                "(129,42,'Hangzhou'),\n" +
                "(130,42,'Foshan'),\n" +
                "(131,42,'Nanquim'),\n" +
                "(132,42,'Chengdu'),\n" +
                "(133,42,'Zhengzhou'),\n" +
                "(134,43,'Limassol'),\n" +
                "(135,44,'Medellín'),\n" +
                "(136,44,'Cali'),\n" +
                "(137,44,'Barranquilla'),\n" +
                "(138,44,'Cartagena das Índias'),\n" +
                "(139,44,'Cúcuta'),\n" +
                "(140,44,'Soledad'),\n" +
                "(141,46,'Hamhung'),\n" +
                "(142,46,'Chongjin'),\n" +
                "(143,47,'Busan'),\n" +
                "(144,47,'Incheon'),\n" +
                "(145,47,'Daegu'),\n" +
                "(146,47,'Daejeon'),\n" +
                "(147,47,'Gwangju'),\n" +
                "(148,47,'Ulsan'),\n" +
                "(149,47,'Suwon'),\n" +
                "(150,47,'Changwon'),\n" +
                "(151,47,'Seongnam'),\n" +
                "(152,47,'Goyang'),\n" +
                "(153,48,'Yamoussoukro'),\n" +
                "(154,49,'Alajuela'),\n" +
                "(155,50,'Split'),\n" +
                "(156,50,'Osijek'),\n" +
                "(157,50,'Rijeka'),\n" +
                "(158,51,'Camagüey'),\n" +
                "(159,51,'Guantánamo'),\n" +
                "(160,52,'Aarhus'),\n" +
                "(161,52,'Odense'),\n" +
                "(162,52,'Ålborg'),\n" +
                "(163,55,'Alexandria'),\n" +
                "(164,55,'Gizé'),\n" +
                "(165,55,'Shubra El Keima'),\n" +
                "(166,55,'Porto Saíde'),\n" +
                "(167,55,'Suez'),\n" +
                "(168,56,'Santa Ana'),\n" +
                "(169,56,'Soyapango'),\n" +
                "(170,57,'Dubai'),\n" +
                "(171,57,'Sharjah'),\n" +
                "(172,58,'Guayaquil'),\n" +
                "(173,60,'Košice'),\n" +
                "(174,61,'Maribor'),\n" +
                "(175,62,'Barcelona'),\n" +
                "(176,62,'Sevilla'),\n" +
                "(177,62,'Valência'),\n" +
                "(178,62,'Málaga'),\n" +
                "(179,62,'Múrcia'),\n" +
                "(180,62,'Las Palmas'),\n" +
                "(181,62,'Zaragoza'),\n" +
                "(182,62,'Palma de Mallorca'),\n" +
                "(183,62,'Bilbao'),\n" +
                "(184,63,'Nova Iorque'),\n" +
                "(185,63,'Los Angeles'),\n" +
                "(186,63,'Chicago'),\n" +
                "(187,63,'Dallas'),\n" +
                "(188,63,'Houston'),\n" +
                "(189,63,'Philadelphia'),\n" +
                "(190,63,'Miami'),\n" +
                "(191,63,'Atlanta'),\n" +
                "(192,63,'Boston'),\n" +
                "(193,63,'San Francisco'),\n" +
                "(194,63,'Phoenix'),\n" +
                "(195,63,'Detroit'),\n" +
                "(196,63,'Seattle'),\n" +
                "(197,63,'Minneapolis'),\n" +
                "(198,63,'San Antonio'),\n" +
                "(199,63,'San Diego'),\n" +
                "(200,63,'Indianápolis'),\n" +
                "(201,63,'Baltimore'),\n" +
                "(202,63,'Memphis'),\n" +
                "(203,63,'Austin'),\n" +
                "(204,63,'Columbus'),\n" +
                "(205,63,'Jacksonville'),\n" +
                "(206,64,'Tartu'),\n" +
                "(207,65,'Adama'),\n" +
                "(208,65,'Mek''ele'),\n" +
                "(209,67,'Cidade Quezon'),\n" +
                "(210,67,'Caloocan'),\n" +
                "(211,67,'Davao'),\n" +
                "(212,67,'Cebu'),\n" +
                "(213,67,'Zamboanga'),\n" +
                "(214,68,'Tampere'),\n" +
                "(215,69,'Marselha'),\n" +
                "(216,69,'Lyon'),\n" +
                "(217,69,'Toulouse'),\n" +
                "(218,69,'Nice'),\n" +
                "(219,69,'Nantes'),\n" +
                "(220,69,'Strasbourg'),\n" +
                "(221,69,'Montpellier'),\n" +
                "(222,69,'Bordeaux'),\n" +
                "(223,69,'Lille'),\n" +
                "(224,69,'Rennes'),\n" +
                "(225,69,'Reims'),\n" +
                "(226,69,'Le Havre'),\n" +
                "(227,70,'Port-Gentil'),\n" +
                "(228,72,'Kumasi'),\n" +
                "(229,72,'Tamale'),\n" +
                "(230,73,'Batumi'),\n" +
                "(231,73,'Kutaisi'),\n" +
                "(232,73,'Rustavi'),\n" +
                "(233,76,'Salonica'),\n" +
                "(234,76,'Patras'),\n" +
                "(235,76,'Larissa'),\n" +
                "(236,76,'Heraclião'),\n" +
                "(237,78,'Camayenne'),\n" +
                "(238,80,'Bata'),\n" +
                "(239,81,'Carrefour'),\n" +
                "(240,81,'Delmas'),\n" +
                "(241,82,'San Pedro Sula'),\n" +
                "(242,82,'Choloma'),\n" +
                "(243,83,'Debrecen'),\n" +
                "(244,83,'Miskolc'),\n" +
                "(245,83,'Szeged'),\n" +
                "(246,83,'Nyíregyháza'),\n" +
                "(247,84,'Al Hudaydah'),\n" +
                "(248,84,'Taiz'),\n" +
                "(249,84,'Adém'),\n" +
                "(250,84,'Mucala')");


    }
    private void popularTabelaCidades2(SQLiteDatabase db) {
        db.execSQL("INSERT INTO cidades VALUES" +
                "(251,87,'Bombaim'),\n" +
                "(252,87,'Bangalor'),\n" +
                "(253,87,'Haiderabade'),\n" +
                "(254,87,'Amedabade'),\n" +
                "(255,87,'Chenai'),\n" +
                "(256,87,'Calcutá'),\n" +
                "(257,87,'Agra'),\n" +
                "(258,87,'Surrate'),\n" +
                "(259,87,'Varanasi'),\n" +
                "(260,87,'Pune'),\n" +
                "(261,88,'Surabaia'),\n" +
                "(262,88,'Bandung'),\n" +
                "(263,88,'Bekasi'),\n" +
                "(264,88,'Medan'),\n" +
                "(265,88,'Tangerang'),\n" +
                "(266,88,'Depok'),\n" +
                "(267,89,'Meched'),\n" +
                "(268,89,'Isfahã'),\n" +
                "(269,89,'Karaj'),\n" +
                "(270,89,'Tabriz'),\n" +
                "(271,89,'Xiraz'),\n" +
                "(272,89,'Avaz'),\n" +
                "(273,89,'Qom'),\n" +
                "(274,90,'Bassora'),\n" +
                "(275,90,'Mossul'),\n" +
                "(276,90,'Arbil'),\n" +
                "(277,90,'Kirkuk'),\n" +
                "(278,91,'Cork'),\n" +
                "(279,93,'Tel Aviv'),\n" +
                "(280,93,'Haifa'),\n" +
                "(281,93,'Rishon LeZion'),\n" +
                "(282,93,'Petah Tikva'),\n" +
                "(283,93,'Nazaré'),\n" +
                "(284,94,'Milão'),\n" +
                "(285,94,'Nápoles'),\n" +
                "(286,94,'Turim'),\n" +
                "(287,94,'Palermo'),\n" +
                "(288,94,'Gênova'),\n" +
                "(289,94,'Bolonha'),\n" +
                "(290,94,'Florença'),\n" +
                "(291,94,'Bari'),\n" +
                "(292,94,'Veneza'),\n" +
                "(293,94,'Verona'),\n" +
                "(294,94,'Parma'),\n" +
                "(295,94,'Catânia'),\n" +
                "(296,95,'New Kingston'),\n" +
                "(297,96,'Yokohama'),\n" +
                "(298,96,'Osaka'),\n" +
                "(299,96,'Nagoia'),\n" +
                "(300,96,'Sapporo'),\n" +
                "(301,96,'Kobe'),\n" +
                "(302,96,'Quioto'),\n" +
                "(303,96,'Fukuoka'),\n" +
                "(304,96,'Kawasaki'),\n" +
                "(305,96,'Saitama'),\n" +
                "(306,96,'Hiroshima'),\n" +
                "(307,96,'Sendai'),\n" +
                "(308,96,'Kitakyushu'),\n" +
                "(309,96,'Chiba'),\n" +
                "(310,96,'Sakai'),\n" +
                "(311,96,'Niigata'),\n" +
                "(312,96,'Hamamatsu'),\n" +
                "(313,96,'Shizuoka'),\n" +
                "(314,96,'Nagasaki'),\n" +
                "(315,97,'Zarqa'),\n" +
                "(316,97,'Irbid'),\n" +
                "(317,97,'Russeifa'),\n" +
                "(318,98,'Prizren'),\n" +
                "(319,105,'Bengazi'),\n" +
                "(320,107,'Kaunas'),\n" +
                "(321,107,'Klaip?da'),\n" +
                "(322,110,'Toamasina'),\n" +
                "(323,111,'Johor Bahru'),\n" +
                "(324,111,'Ipoh'),\n" +
                "(325,111,'Shah Alam'),\n" +
                "(326,111,'Petaling Jaya'),\n" +
                "(327,111,'Kuching'),\n" +
                "(328,111,'Kota Kinabalu'),\n" +
                "(329,111,'Kuala Terengganu'),\n" +
                "(330,112,'Blantire'),\n" +
                "(331,116,'Casablanca'),\n" +
                "(332,116,'Tânger'),\n" +
                "(333,116,'Marrakech'),\n" +
                "(334,119,'Guadalajara'),\n" +
                "(335,119,'Monterrei'),\n" +
                "(336,119,'Puebla'),\n" +
                "(337,119,'Toluca'),\n" +
                "(338,119,'Tijuana'),\n" +
                "(339,119,'Juárez'),\n" +
                "(340,119,'Querétaro'),\n" +
                "(341,119,'Acapulco'),\n" +
                "(342,119,'Chihuahua'),\n" +
                "(343,119,'Mérida'),\n" +
                "(344,121,'Nampula'),\n" +
                "(345,121,'Matola'),\n" +
                "(346,121,'Beira'),\n" +
                "(347,122,'Tiraspol'),\n" +
                "(348,126,'Rangun'),\n" +
                "(349,126,'Mandalay'),\n" +
                "(350,129,'Birganj'),\n" +
                "(351,129,'Pokhara'),\n" +
                "(352,130,'León'),\n" +
                "(353,130,'Masaya'),\n" +
                "(354,131,'Zinder'),\n" +
                "(355,131,'Maradi'),\n" +
                "(356,132,'Lagos'),\n" +
                "(357,132,'Cano'),\n" +
                "(358,132,'Ibadan'),\n" +
                "(359,132,'Kaduna'),\n" +
                "(360,132,'Porto Harcourt'),\n" +
                "(361,132,'Maiduguri'),\n" +
                "(362,132,'Zaria'),\n" +
                "(363,132,'Aba'),\n" +
                "(364,133,'Bergen'),\n" +
                "(365,133,'Trondheim'),\n" +
                "(366,133,'Stavanger'),\n" +
                "(367,133,'Longyearbyen'),\n" +
                "(368,134,'Auckland'),\n" +
                "(369,134,'Christchurch'),\n" +
                "(370,135,'Seebe'),\n" +
                "(371,136,'Rotterdam'),\n" +
                "(372,136,'Den Haag'),\n" +
                "(373,136,'Utrecht'),\n" +
                "(374,136,'Eindhoven'),\n" +
                "(375,136,'Tilburg'),\n" +
                "(376,136,'Almere'),\n" +
                "(377,136,'Groningen'),\n" +
                "(378,139,'San Miguelito'),\n" +
                "(379,141,'Karachi'),\n" +
                "(380,141,'Laore'),\n" +
                "(381,141,'Faisalabad'),\n" +
                "(382,141,'Rawalpindi'),\n" +
                "(383,141,'Multan'),\n" +
                "(384,141,'Hyderabad'),\n" +
                "(385,141,'Gujranwala'),\n" +
                "(386,142,'Ciudad del Este'),\n" +
                "(387,142,'Luque'),\n" +
                "(388,142,'San Lorenzo'),\n" +
                "(389,142,'Capiatá'),\n" +
                "(390,142,'Fernando de la Mora'),\n" +
                "(391,143,'Arequipa'),\n" +
                "(392,143,'Trujillo'),\n" +
                "(393,143,'Chiclayo'),\n" +
                "(394,143,'Cusco'),\n" +
                "(395,143,'Nazca'),\n" +
                "(396,143,'Huancayo'),\n" +
                "(397,145,'Cracóvia'),\n" +
                "(398,145,'?ód?'),\n" +
                "(399,145,'Wroc?aw'),\n" +
                "(400,145,'Pozna?'),\n" +
                "(401,145,'Gda?sk'),\n" +
                "(402,145,'Bydgoszcz'),\n" +
                "(403,145,'Lublin'),\n" +
                "(404,145,'Katowice'),\n" +
                "(405,146,'Sintra'),\n" +
                "(406,146,'Vila Nova de Gaia'),\n" +
                "(407,146,'Porto'),\n" +
                "(408,146,'Cascais'),\n" +
                "(409,146,'Loures'),\n" +
                "(410,146,'Braga'),\n" +
                "(411,146,'Guimarães'),\n" +
                "(412,146,'Coimbra'),\n" +
                "(413,146,'Almada'),\n" +
                "(414,146,'Oieiras'),\n" +
                "(415,147,'Mombaça'),\n" +
                "(416,150,'Manchester'),\n" +
                "(417,150,'Birmingham'),\n" +
                "(418,150,'Leeds'),\n" +
                "(419,150,'Glasgow'),\n" +
                "(420,150,'Liverpool'),\n" +
                "(421,150,'Sheffield'),\n" +
                "(422,150,'Belfast'),\n" +
                "(423,150,'Edimburgo'),\n" +
                "(424,150,'Cardiff'),\n" +
                "(425,150,'Tyneside'),\n" +
                "(426,150,'Nottingham'),\n" +
                "(427,150,'Bristol'),\n" +
                "(428,150,'Brighton'),\n" +
                "(429,150,'Leicester'),\n" +
                "(430,150,'Reading'),\n" +
                "(431,152,'Mbuji-Mayi'),\n" +
                "(432,152,'Lubumbashi'),\n" +
                "(433,152,'Kananga'),\n" +
                "(434,153,'Pointe-Noire'),\n" +
                "(435,154,'Santiago de los Caballeros'),\n" +
                "(436,155,'Brno'),\n" +
                "(437,155,'Ostrava'),\n" +
                "(438,155,'Plze?'),\n" +
                "(439,158,'São Petersburgo'),\n" +
                "(440,158,'Novosibirsk'),\n" +
                "(441,158,'Ecaterimburgo'),\n" +
                "(442,158,'Nijni Novgorod'),\n" +
                "(443,158,'Samara'),\n" +
                "(444,158,'Cazã'),\n" +
                "(445,158,'Rostov do Don'),\n" +
                "(446,158,'Tcheliabinsk'),\n" +
                "(447,158,'Volgogrado'),\n" +
                "(448,158,'Krasnodar'),\n" +
                "(449,158,'Ufá'),\n" +
                "(450,158,'Krasnoyarsk'),\n" +
                "(451,166,'Touba'),\n" +
                "(452,168,'Novi Sad'),\n" +
                "(453,170,'Alepo'),\n" +
                "(454,170,'Homs'),\n" +
                "(455,170,'Latakia'),\n" +
                "(456,170,'Hama'),\n" +
                "(457,170,'Ar-Raqqah'),\n" +
                "(458,174,'Ondurmã'),\n" +
                "(459,176,'Göteborg'),\n" +
                "(460,176,'Malmö'),\n" +
                "(461,177,'Zurique'),\n" +
                "(462,177,'Genebra'),\n" +
                "(463,177,'Basel'),\n" +
                "(464,177,'Lausanne'),\n" +
                "(465,177,'Winterthur'),\n" +
                "(466,187,'Sfax'),\n" +
                "(467,187,'Susa'),\n" +
                "(468,188,'Türkmenabat'),\n" +
                "(469,189,'Istambul'),\n" +
                "(470,189,'Esmirna'),\n" +
                "(471,189,'Bursa'),\n" +
                "(472,189,'Adana'),\n" +
                "(473,189,'Gaziantepe'),\n" +
                "(474,191,'Donetsk'),\n" +
                "(475,191,'Odessa'),\n" +
                "(476,191,'Dnipro'),\n" +
                "(477,191,'Kharkiv'),\n" +
                "(478,197,'Maracaibo'),\n" +
                "(479,197,'Barquisimeto'),\n" +
                "(480,197,'San Cristóbal'),\n" +
                "(481,198,'Cidade de Ho Chi Minh'),\n" +
                "(482,198,'Haifom'),\n" +
                "(483,198,'Can Tho'),\n" +
                "(484,200,'Mixco'),\n" +
                "(485,200,'Villa Nueva')");
    }
    private void popularTabelaCategorias(SQLiteDatabase db) {
        db.execSQL("INSERT INTO `categorias` VALUES " +
                "(1,'capital',0,0)," +
                "(2,'bandeira',0,0)," +
                "(3,'idioma',0,0)," +
                "(4,'populacao',0,0)," +
                "(5,'area',0,0)," +
                "(6,'continente',1,1)," +
                "(7,'moeda',0,0)," +
                "(8,'emblema',0,0)," +
                "(9,'mapa',0,0)," +
                "(10,'cidades',0,0)");

    }

    private void popularTabelaHabilitaQtdPerguntas(SQLiteDatabase db){
        db.execSQL("INSERT INTO `habilitaQtdPerguntas` " +
                "VALUES " +
                "(1,'Partidas de 15',1,0)," +
                "(2,'Partidas de 30',0,10)," +
                "(3,'Partidas de 50',0,10)\n");
    }
    private void popularTabelaAlternativas1(SQLiteDatabase db) {
        db.execSQL("INSERT INTO `alternativas` VALUES(1,' Cabul ','1','0','0'),\n" +
                "(2,' Pretória ','1','0','0'),\n" +
                "(3,' Tirana ','1','0','0'),\n" +
                "(4,' Berlim ','1','0','0'),\n" +
                "(5,' Andorra a velha ','1','0','0'),\n" +
                "(6,' Luanda ','1','0','0'),\n" +
                "(7,' The Valley ','1','0','0'),\n" +
                "(8,' Saint Johns ','1','0','0'),\n" +
                "(9,' Riade ','1','0','0'),\n" +
                "(10,' Argel ','1','0','0'),\n" +
                "(11,' Buenos Aires ','1','0','0'),\n" +
                "(12,' Erevã ','1','0','0')," +
                "(13,' Oranjestad ','1','0','0'),\n" +
                "(14,' Camberra ','1','0','0'),\n" +
                "(15,' Viena ','1','0','0'),\n" +
                "(16,' Baku ','1','0','0'),\n" +
                "(17,' Nassau ','1','0','0'),\n" +
                "(18,' Daca ','1','0','0'),\n" +
                "(19,' Bridgetown ','1','0','0'),\n" +
                "(20,' Manama ','1','0','0'),\n" +
                "(21,' Bruxelas ','1','0','0'),\n" +
                "(22,' Belmopan ','1','0','0'),\n" +
                "(23,' Porto Novo ','1','0','0'),\n" +
                "(24,' Minsk ','1','0','0'),\n" +
                "(25,' Sucre ','1','0','0'),\n" +
                "(26,' Sarajevo ','1','0','0'),\n" +
                "(27,' Gaborone ','1','0','0'),\n" +
                "(28,' Brasília ','1','0','0'),\n" +
                "(29,' Bandar Seri Begawan ','1','0','0'),\n" +
                "(30,' Sofia ','1','0','0'),\n" +
                "(31,' Ouagadougou ','1','0','0'),\n" +
                "(32,' Bujumbura ','1','0','0'),\n" +
                "(33,' Thimbu ','1','0','0'),\n" +
                "(34,' Praia ','1','0','0')," +
                "(35,' Yaoundé ','1','0','0'),\n" +
                "(36,' Phnom Penh ','1','0','0'),\n" +
                "(37,' Ottawa ','1','0','0'),\n" +
                "(38,' Doha ','1','0','0'),\n" +
                "(39,' Astana ','1','0','0'),\n" +
                "(40,' NDjamena ','1','0','0'),\n" +
                "(41,' Santiago ','1','0','0'),\n" +
                "(42,' Pequim ','1','0','0'),\n" +
                "(43,' Nicósia ','1','0','0'),\n" +
                "(44,' Bogotá ','1','0','0'),\n" +
                "(45,' Moroni ','1','0','0'),\n" +
                "(46,' Pyongyang ','1','0','0'),\n" +
                "(47,' Seul ','1','0','0'),\n" +
                "(48,' Abidjã ','1','0','0'),\n" +
                "(49,' San José ','1','0','0'),\n" +
                "(50,' Zagreb ','1','0','0'),\n" +
                "(51,' Havana ','1','0','0'),\n" +
                "(52,' Copenhague ','1','0','0'),\n" +
                "(53,' Djbuti ','1','0','0'),\n" +
                "(54,' Roseau ','1','0','0'),\n" +
                "(55,' Cairo ','1','0','0'),\n" +
                "(56,' San Salvador ','1','0','0'),\n" +
                "(57,' Abu Dhabi ','1','0','0'),\n" +
                "(58,' Quito ','1','0','0'),\n" +
                "(59,' Asmara ','1','0','0')," +
                "(60,' Bratislava ','1','0','0'),\n" +
                "(61,' Liubliana ','1','0','0'),\n" +
                "(62,' Madrid ','1','0','0'),\n" +
                "(63,' Washington ','1','0','0'),\n" +
                "(64,' Talin ','1','0','0'),\n" +
                "(65,' Adis Abeba ','1','0','0'),\n" +
                "(66,' Suva ','1','0','0'),\n" +
                "(67,' Manila ','1','0','0'),\n" +
                "(68,' Helsinque ','1','0','0'),\n" +
                "(69,' Paris ','1','0','0'),\n" +
                "(70,' Libreville ','1','0','0'),\n" +
                "(71,' Banjul ','1','0','0'),\n" +
                "(72,' Acra ','1','0','0'),\n" +
                "(73,' Tbilisi ','1','0','0'),\n" +
                "(74,' Gibraltar ','1','0','0'),\n" +
                "(75,' Saint Georges ','1','0','0'),\n" +
                "(76,' Atenas ','1','0','0'),\n" +
                "(77,' Georgetown ','1','0','0'),\n" +
                "(78,' Conacri ','1','0','0'),\n" +
                "(79,' Bissau ','1','0','0'),\n" +
                "(80,' Malabo ','1','0','0'),\n" +
                "(81,' Porto Príncipe ','1','0','0'),\n" +
                "(82,' Tegucigalpa ','1','0','0'),\n" +
                "(83,' Budapeste ','1','0','0')," +
                "(84,' Sana ','1','0','0'),\n" +
                "(85,' Majuro ','1','0','0'),\n" +
                "(86,' Honiara ','1','0','0'),\n" +
                "(87,' Deli ','1','0','0'),\n" +
                "(88,' Jacarta ','1','0','0'),\n" +
                "(89,' Teerã ','1','0','0'),\n" +
                "(90,' Bagdá ','1','0','0'),\n" +
                "(91,' Dublin ','1','0','0'),\n" +
                "(92,' Reykjavík ','1','0','0'),\n" +
                "(93,' Jerusalem ','1','0','0'),\n" +
                "(94,' Roma ','1','0','0'),\n" +
                "(95,' Kingston ','1','0','0'),\n" +
                "(96,' Tóquio ','1','0','0'),\n" +
                "(97,' Amã ','1','0','0'),\n" +
                "(98,' Pristina ','1','0','0'),\n" +
                "(99,' Cidade do Kuwait ','1','0','0'),\n" +
                "(100,' Vientiane ','1','0','0'),\n" +
                "(101,' Maseru ','1','0','0'),\n" +
                "(102,' Riga ','1','0','0'),\n" +
                "(103,' Beirute ','1','0','0'),\n" +
                "(104,' Monróvia ','1','0','0'),\n" +
                "(105,' Trípoli ','1','0','0'),\n" +
                "(106,' Vaduz ','1','0','0')," +
                "(107,' Vilnius ','1','0','0'),\n" +
                "(108,' Luxemburgo ','1','0','0'),\n" +
                "(109,' Escópia ','1','0','0'),\n" +
                "(110,' Antananarivo ','1','0','0'),\n" +
                "(111,' Kuala Lumpur ','1','0','0'),\n" +
                "(112,' Lilongwe ','1','0','0'),\n" +
                "(113,' Malé ','1','0','0'),\n" +
                "(114,' Bamako ','1','0','0'),\n" +
                "(115,' Valeta ','1','0','0'),\n" +
                "(116,' Rabat ','1','0','0'),\n" +
                "(117,' Port Louis ','1','0','0'),\n" +
                "(118,' Nouakchott ','1','0','0'),\n" +
                "(119,' Cidade do México ','1','0','0'),\n" +
                "(120,' Palikir ','1','0','0'),\n" +
                "(121,' Maputo ','1','0','0'),\n" +
                "(122,' Chisinau ','1','0','0'),\n" +
                "(123,' Monaco Ville ','1','0','0'),\n" +
                "(124,' Ulan Bator ','1','0','0'),\n" +
                "(125,' Podgorica ','1','0','0'),\n" +
                "(126,' Naypyidaw ','1','0','0'),\n" +
                "(127,' Windhoek ','1','0','0'),\n" +
                "(128,' Yaren ','1','0','0')," +
                "(129,' Catmandu ','1','0','0'),\n" +
                "(130,' Manágua ','1','0','0'),\n" +
                "(131,' Niamey ','1','0','0'),\n" +
                "(132,' Abuja ','1','0','0'),\n" +
                "(133,' Oslo ','1','0','0'),\n" +
                "(134,' Welington ','1','0','0'),\n" +
                "(135,' Mascate ','1','0','0'),\n" +
                "(136,' Amsterdã ','1','0','0'),\n" +
                "(137,' Melekeok ','1','0','0'),\n" +
                "(138,' Ramallah ','1','0','0'),\n" +
                "(139,' Cidade do Panamá ','1','0','0'),\n" +
                "(140,' Port Moresby ','1','0','0'),\n" +
                "(141,' Islamabad ','1','0','0'),\n" +
                "(142,' Assunção ','1','0','0'),\n" +
                "(143,' Lima ','1','0','0'),\n" +
                "(144,' Papeete ','1','0','0'),\n" +
                "(145,' Varsóvia ','1','0','0'),\n" +
                "(146,' Lisboa ','1','0','0'),\n" +
                "(147,' Nairóbi ','1','0','0'),\n" +
                "(148,' Bisqueque ','1','0','0'),\n" +
                "(149,' Tarawa do Sul ','1','0','0'),\n" +
                "(150,' Londres ','1','0','0'),\n" +
                "(151,' Bangui ','1','0','0'),\n" +
                "(152,' Kinshasa ','1','0','0'),\n" +
                "(153,' Brazzaville ','1','0','0'),\n" +
                "(154,' Santo Domingo ','1','0','0'),\n" +
                "(155,' Praga ','1','0','0')," +
                "(156,' Bucareste ','1','0','0'),\n" +
                "(157,' Kigali ','1','0','0'),\n" +
                "(158,' Moscou ','1','0','0'),\n" +
                "(159,' Apia ','1','0','0'),\n" +
                "(160,' São Marinho ','1','0','0'),\n" +
                "(161,' Castries ','1','0','0'),\n" +
                "(162,' Brasseferre ','1','0','0'),\n" +
                "(163,' São Tomé ','1','0','0'),\n" +
                "(164,' Kingstown ','1','0','0'),\n" +
                "(165,' Victoria ','1','0','0'),\n" +
                "(166,' Dakar ','1','0','0'),\n" +
                "(167,' Freetown ','1','0','0'),\n" +
                "(168,' Belgrado ','1','0','0'),\n" +
                "(169,' Singapura ','1','0','0'),\n" +
                "(170,' Damasco ','1','0','0'),\n" +
                "(171,' Mogadíscio ','1','0','0'),\n" +
                "(172,' Kotte ','1','0','0'),\n" +
                "(173,' Mbabane ','1','0','0'),\n" +
                "(174,' Cartum ','1','0','0'),\n" +
                "(175,' Juba ','1','0','0'),\n" +
                "(176,' Estocolmo ','1','0','0'),\n" +
                "(177,' Berna ','1','0','0'),\n" +
                "(178,' Paramaribo ','1','0','0'),\n" +
                "(179,' Bangcoc ','1','0','0'),\n" +
                "(180,' Taipé ','1','0','0'),\n" +
                "(181,' Duchambe ','1','0','0'),\n" +
                "(182,' Dodoma ','1','0','0')," +
                "(183,' Dili ','1','0','0'),\n" +
                "(184,' Lomé ','1','0','0'),\n" +
                "(185,' Nukualofa ','1','0','0'),\n" +
                "(186,' Port of Spain ','1','0','0'),\n" +
                "(187,' Túnis ','1','0','0'),\n" +
                "(188,' Asgabate ','1','0','0'),\n" +
                "(189,' Ancara ','1','0','0'),\n" +
                "(190,' Funafuti ','1','0','0'),\n" +
                "(191,' Kiev ','1','0','0'),\n" +
                "(192,' Kampala ','1','0','0'),\n" +
                "(193,' Montevideu ','1','0','0'),\n" +
                "(194,' Tashkent ','1','0','0'),\n" +
                "(195,' Port Vila ','1','0','0'),\n" +
                "(196,' Vaticano ','1','0','0'),\n" +
                "(197,' Caracas ','1','0','0'),\n" +
                "(198,' Hanói ','1','0','0'),\n" +
                "(199,' Lusaka ','1','0','0'),\n" +
                "(200,' Harare ','1','0','0'),\n" +
                "(201,'Africâner','3','0','0'),\n" +
                "(202,'Aimará','3','0','0'),\n" +
                "(203,'Albanês','3','0','0'),\n" +
                "(204,'Alemão','3','0','0'),\n" +
                "(205,'Árabe','3','0','0'),\n" +
                "(206,'Armênio','3','0','0')," +
                "(207,'Azeri','3','0','0'),\n" +
                "(208,'Bielorusso','3','0','0'),\n" +
                "(209,'Bengali','3','0','0'),\n" +
                "(210,'Bislama','3','0','0'),\n" +
                "(211,'Birmanês','3','0','0'),\n" +
                "(212,'Bósnio','3','0','0'),\n" +
                "(213,'Búlgaro','3','0','0'),\n" +
                "(214,'Cazaque','3','0','0'),\n" +
                "(215,'Catalão','3','0','0'),\n" +
                "(216,'Tcheco','3','0','0'),\n" +
                "(217,'Mandarim','3','0','0'),\n" +
                "(218,'Coreano','3','0','0'),\n" +
                "(219,'Croata','3','0','0'),\n" +
                "(220,'Curdo','3','0','0'),\n" +
                "(221,'Dinamarquês','3','0','0'),\n" +
                "(222,'Dari','3','0','0'),\n" +
                "(223,'Divehi','3','0','0'),\n" +
                "(224,'Dzongkha','3','0','0'),\n" +
                "(225,'Eslovaco','3','0','0'),\n" +
                "(226,'Esloveno','3','0','0'),\n" +
                "(227,'Estoniano','3','0','0'),\n" +
                "(228,'Espanhol','3','0','0'),\n" +
                "(229,'Fijiano','3','0','0'),\n" +
                "(230,'Filipino','3','0','0'),\n" +
                "(231,'Finlandês','3','0','0')," +
                "(232,'Francês','3','0','0'),\n" +
                "(233,'Frísio','3','0','0'),\n" +
                "(234,'Gerogiano','3','0','0'),\n" +
                "(235,'Grego','3','0','0'),\n" +
                "(236,'Guaraní','3','0','0'),\n" +
                "(237,'Crioulo','3','0','0'),\n" +
                "(238,'Hebraico','3','0','0'),\n" +
                "(239,'Hindi','3','0','0'),\n" +
                "(240,'Hiri Motu','3','0','0'),\n" +
                "(241,'Holandês','3','0','0'),\n" +
                "(242,'Húngaro','3','0','0'),\n" +
                "(243,'Indonésio','3','0','0'),\n" +
                "(244,'Inglês','3','0','0'),\n" +
                "(245,'Irlandês','3','0','0'),\n" +
                "(246,'Italiano','3','0','0'),\n" +
                "(247,'Japonês','3','0','0'),\n" +
                "(248,'Canará','3','0','0'),\n" +
                "(249,'Khmer','3','0','0'),\n" +
                "(250,'Laociano','3','0','0')");



    }
    private void popularTabelaAlternativas2(SQLiteDatabase db) {
        db.execSQL("INSERT INTO `alternativas` VALUES" +
                "(251,'Latim','3','0','0'),\n" +
                "(252,'Letão','3','0','0'),\n" +
                "(253,'Lituano','3','0','0')," +
                "(254,'Luxemburguês','3','0','0'),\n" +
                "(255,'Macedônio','3','0','0'),\n" +
                "(256,'Malaio','3','0','0'),\n" +
                "(257,'Malaiala','3','0','0'),\n" +
                "(258,'Maori','3','0','0'),\n" +
                "(259,'Marata','3','0','0'),\n" +
                "(260,'Marshalês','3','0','0'),\n" +
                "(261,'Montenegrino','3','0','0'),\n" +
                "(262,'Moldávio','3','0','0'),\n" +
                "(263,'Mongol','3','0','0'),\n" +
                "(264,'Ndebele','3','0','0'),\n" +
                "(265,'Nepalês','3','0','0'),\n" +
                "(266,'Sotho','3','0','0'),\n" +
                "(267,'Norueguês','3','0','0'),\n" +
                "(268,'Oriá','3','0','0'),\n" +
                "(269,'Pachto','3','0','0'),\n" +
                "(270,'Persa','3','0','0'),\n" +
                "(271,'Polonês','3','0','0'),\n" +
                "(272,'Português','3','0','0'),\n" +
                "(273,'Punjabi','3','0','0'),\n" +
                "(274,'Quíchua','3','0','0')," +
                "(275,'Quiniaruanda','3','0','0'),\n" +
                "(276,'Quirguiz','3','0','0'),\n" +
                "(277,'Romanche','3','0','0'),\n" +
                "(278,'Romeno','3','0','0'),\n" +
                "(279,'Russo','3','0','0'),\n" +
                "(280,'Sérvio','3','0','0'),\n" +
                "(281,'Cingalês','3','0','0'),\n" +
                "(282,'Somali','3','0','0'),\n" +
                "(283,'Sango','3','0','0'),\n" +
                "(284,'Soto','3','0','0'),\n" +
                "(285,'Suaíli','3','0','0'),\n" +
                "(286,'Suázi','3','0','0'),\n" +
                "(287,'Sueco','3','0','0'),\n" +
                "(288,'Tajique','3','0','0'),\n" +
                "(289,'Tâmil','3','0','0'),\n" +
                "(290,'Tétum','3','0','0'),\n" +
                "(291,'Tailandês','3','0','0'),\n" +
                "(292,'Tigrínio','3','0','0'),\n" +
                "(293,'Tok Pisin','3','0','0'),\n" +
                "(294,'Tsonga','3','0','0'),\n" +
                "(295,'Tonganês','3','0','0'),\n" +
                "(296,'Tswana','3','0','0')," +
                "(297,'Turco','3','0','0'),\n" +
                "(298,'Turcomeno','3','0','0'),\n" +
                "(299,'Tuvaluano','3','0','0'),\n" +
                "(300,'Ucrniano','3','0','0'),\n" +
                "(301,'Venda','3','0','0'),\n" +
                "(302,'Vietnamita','3','0','0'),\n" +
                "(303,'Xhosa','3','0','0'),\n" +
                "(304,'Zulu','3','0','0'),\n" +
                "(305,'Inglês/Africaner/Zulu/Ndebele/Xhosa/Sotho/Suázi/Venda/Tsonga/Tswana','3','0','0'),\n" +
                "(306,'Grego / Turco','3','0','0'),\n" +
                "(307,'Inglês / Francês','3','0','0'),\n" +
                "(308,'Árabe / Francês','3','0','0'),\n" +
                "(309,'Chinês / Português','3','0','0'),\n" +
                "(310,'Malgaxe','3','0','0'),\n" +
                "(311,'Malgaxe / Francês','3','0','0'),\n" +
                "(312,'Maltês / Inglês','3','0','0'),\n" +
                "(313,'Inglês / Maori','3','0','0'),\n" +
                "(314,'Espanhol / Guarani','3','0','0'),\n" +
                "(315,'Espanhol / Quíchua','3','0','0'),\n" +
                "(316,'Quirguiz / Russo','3','0','0'),\n" +
                "(317,'Samoano / Inglês','3','0','0'),\n" +
                "(318,'Alemão / Francês / Italiano / Romanche','3','0','0')," +
                "(319,'Português / Tétum','3','0','0'),\n" +
                "(320,'Inglês / Espanhol','3','0','0'),\n" +
                "(321,'Bislama / Inglês / Francês','3','0','0'),\n" +
                "(322,'Italiano / Latim','3','0','0'),\n" +
                "(323,'Holandês / Alemão / Francês','3','0','0'),\n" +
                "(324,'Bósnia / Sérvio / Croata','3','0','0'),\n" +
                "(325,'Inglês / Francês /Kirundi','3','0','0'),\n" +
                "(326,'Kirundi','3','0','0'),\n" +
                "(327,'Inglês / Filipino','3','0','0'),\n" +
                "(328,'Finlandês / Sueco','3','0','0'),\n" +
                "(329,'Espanhol / Francês / Português','3','0','0'),\n" +
                "(330,'Crioulo Haitiano','3','0','0'),\n" +
                "(331,'Francês / Crioulo Haitiano','3','0','0'),\n" +
                "(332,'Inglês / Marshalês','3','0','0'),\n" +
                "(333,'Inglês / Hindi','3','0','0'),\n" +
                "(334,'Alemão / Francês / Luxermburguês','3','0','0'),\n" +
                "(335,'Albanês / Macedônio','3','0','0'),\n" +
                "(336,'Inglês / Palauan','3','0','0'),\n" +
                "(337,'Inglês / Tok Pisin / Hiri Motu','3','0','0')," +
                "(338,'Francês / Sango','3','0','0'),\n" +
                "(339,'Francês / Inglês / Suaíli / Quiniaruanda','3','0','0'),\n" +
                "(340,'Malaio / Tamil / Inglês / Mandarim','3','0','0'),\n" +
                "(341,'Inglês / Tonganês','3','0','0'),\n" +
                "(342,'Inglês / Tuvaluano','3','0','0'),\n" +
                "(343,'Russo / Inglês','3','0','0'),\n" +
                "(344,'Espanhol / Romeno','3','0','0'),\n" +
                "(345,'Romeno / Italiano','3','0','0'),\n" +
                "(346,'Francês / Português','3','0','0'),\n" +
                "(347,'Guarani / Quíchua','3','0','0'),\n" +
                "(348,'Menor que 100 mill','4','0','99999'),\n" +
                "(349,'Entre 100 mil e 1 milhão','4','100000','999999'),\n" +
                "(350,'Entre 1 milhão e 10 milhões','4','1000000','9999999'),\n" +
                "(351,'Entre 10 milhões e 50 milhões','4','10000000','49999999'),\n" +
                "(352,'Entre e 50 milhões e 100 milhões','4','50000000','99999999'),\n" +
                "(353,'Entre 100 milhões e 250 milhões','4','100000000','249999999'),\n" +
                "(354,'Entre 250 milhões e 500 milhões','4','250000000','499999999'),\n" +
                "(355,'Mais de 500 milhões','4','500000000','9999999999'),\n" +
                "(356,'0 - 10.000','5','0','9999'),\n" +
                "(357,'10.000 - 50.000','5','10000','49999'),\n" +
                "(358,'50.000 - 100.000','5','50000','99999'),\n" +
                "(359,'100.000 - 500.000','5','100000','499999'),\n" +
                "(360,'500.000 - 1.000.000','5','500000','999999'),\n" +
                "(361,'1.000.000 - 5.000.000','5','1000000','4999999'),\n" +
                "(362,'5.000.000 +','5','5000000','99999999'),\n" +
                "(363,'América','6','0','0')," +
                "(364,'Europa','6','0','0'),\n" +
                "(365,'Ásia','6','0','0'),\n" +
                "(366,'Oceania','6','0','0'),\n" +
                "(367,'África','6','0','0'),\n" +
                "(368,'afegani','7','0','0'),\n" +
                "(369,'ariary','7','0','0'),\n" +
                "(370,'baht tailandês','7','0','0'),\n" +
                "(371,'balboa panamenha','7','0','0'),\n" +
                "(372,'birr etíope','7','0','0'),\n" +
                "(373,'bolivar venezuelano','7','0','0'),\n" +
                "(374,'boliviano','7','0','0'),\n" +
                "(375,'cedi','7','0','0'),\n" +
                "(376,'colón costa riquenho','7','0','0'),\n" +
                "(377,'córdoba','7','0','0'),\n" +
                "(378,'coroa','7','0','0'),\n" +
                "(379,'coroa islandesa','7','0','0'),\n" +
                "(380,'coroa norueguesa','7','0','0'),\n" +
                "(381,'coroa sueca','7','0','0'),\n" +
                "(382,'dalasi','7','0','0'),\n" +
                "(383,'dinar','7','0','0'),\n" +
                "(384,'dinar bareinita','7','0','0'),\n" +
                "(385,'dinar iraquiano','7','0','0'),\n" +
                "(386,'dinar jordano','7','0','0'),\n" +
                "(387,'dinar kwaitianio','7','0','0'),\n" +
                "(388,'dinar líbio','7','0','0')," +
                "(389,'dinar macedônio','7','0','0'),\n" +
                "(390,'dinar sérvio','7','0','0'),\n" +
                "(391,'dinar sudanês','7','0','0'),\n" +
                "(392,'dinar tunisiano','7','0','0'),\n" +
                "(393,'dirham dos emirados','7','0','0'),\n" +
                "(394,'dirham marroquino','7','0','0'),\n" +
                "(395,'Dobra','7','0','0'),\n" +
                "(396,'Dólar Americano','7','0','0'),\n" +
                "(397,'Dólar Australiano','7','0','0'),\n" +
                "(398,'Dólar Baamiano','7','0','0'),\n" +
                "(399,'dólar Barbadense','7','0','0'),\n" +
                "(400,'dólar canadenste','7','0','0'),\n" +
                "(401,'dólar da Nova Zelândia','7','0','0'),\n" +
                "(402,'dólar de Belize','7','0','0'),\n" +
                "(403,'dólar de Brunei','7','0','0'),\n" +
                "(404,'Dólar de fiji','7','0','0'),\n" +
                "(405,'Dólar de Kiribati','7','0','0'),\n" +
                "(406,'Dólar de Salomão','7','0','0'),\n" +
                "(407,'dólar de Singapura','7','0','0'),\n" +
                "(408,'dolar de trindade e tobago','7','0','0'),\n" +
                "(409,'dólar de Tuvalu','7','0','0'),\n" +
                "(410,'Dólar do Caribe','7','0','0'),\n" +
                "(411,'Dólar do Caribe Oriental','7','0','0'),\n" +
                "(412,'dólar do suriname','7','0','0'),\n" +
                "(413,'dólar guianense','7','0','0'),\n" +
                "(414,'dólar jamaicano','7','0','0'),\n" +
                "(415,'dólar liberiano','7','0','0')," +
                "(416,'dólar namibiano','7','0','0'),\n" +
                "(417,'dólar zimbabuano','7','0','0'),\n" +
                "(418,'dong','7','0','0'),\n" +
                "(419,'dram','7','0','0'),\n" +
                "(420,'Escudo','7','0','0'),\n" +
                "(421,'Euro ','7','0','0'),\n" +
                "(422,'Florim arubano','7','0','0'),\n" +
                "(423,'florim húngaro','7','0','0'),\n" +
                "(424,'franco','7','0','0')," +
                "(425,'franco centro africano','7','0','0'),\n" +
                "(426,'franco CFA','7','0','0'),\n" +
                "(427,'Franco CFP','7','0','0'),\n" +
                "(428,'Franco Comoriano','7','0','0'),\n" +
                "(429,'franco congolês','7','0','0'),\n" +
                "(430,'franco de Burundi','7','0','0'),\n" +
                "(431,'franco djboutiano','7','0','0'),\n" +
                "(432,'franco guineense','7','0','0'),\n" +
                "(433,'franco ruandês','7','0','0'),\n" +
                "(434,'Franco Suiço','7','0','0'),\n" +
                "(435,'gourde','7','0','0'),\n" +
                "(436,'grivnia','7','0','0'),\n" +
                "(437,'guarani','7','0','0'),\n" +
                "(438,'iene','7','0','0'),\n" +
                "(439,'kina','7','0','0'),\n" +
                "(440,'kip','7','0','0'),\n" +
                "(441,'kuna croata','7','0','0'),\n" +
                "(442,'kwacha malawiana','7','0','0')," +
                "(443,'kwacha zambiana','7','0','0'),\n" +
                "(444,'kwanza','7','0','0'),\n" +
                "(445,'lari','7','0','0'),\n" +
                "(446,'lek','7','0','0'),\n" +
                "(447,'lempira','7','0','0'),\n" +
                "(448,'leone','7','0','0'),\n" +
                "(449,'leu moldávio','7','0','0'),\n" +
                "(450,'leu romeno','7','0','0'),\n" +
                "(451,'lev','7','0','0'),\n" +
                "(452,'Libra de Gibraltar','7','0','0'),\n" +
                "(453,'libra egípcia','7','0','0'),\n" +
                "(454,'libra esterlina','7','0','0'),\n" +
                "(455,'libra libanesa','7','0','0'),\n" +
                "(456,'libra síria','7','0','0'),\n" +
                "(457,'libra sul sudanesa','7','0','0'),\n" +
                "(458,'lilangeni suazi','7','0','0'),\n" +
                "(459,'lira turca','7','0','0'),\n" +
                "(460,'loti de Lesoto','7','0','0'),\n" +
                "(461,'manat','7','0','0'),\n" +
                "(462,'manate turcomeno','7','0','0'),\n" +
                "(463,'marco conversível','7','0','0'),\n" +
                "(464,'metical','7','0','0'),\n" +
                "(465,'naira','7','0','0'),\n" +
                "(466,'nakfa','7','0','0')," +
                "(467,'ngultrum','7','0','0'),\n" +
                "(468,'novo dólar taiwanês','7','0','0'),\n" +
                "(469,'Cruzeiro','7','0','0'),\n" +
                "(470,'novo shekel','7','0','0'),\n" +
                "(471,'nuevo sol','7','0','0'),\n" +
                "(472,'ouguiya','7','0','0'),\n" +
                "(473,'Paanga','7','0','0'),\n" +
                "(474,'peso','7','0','0'),\n" +
                "(475,'peso','7','0','0'),\n" +
                "(476,'peso argentino','7','0','0'),\n" +
                "(477,'peso chileno','7','0','0'),\n" +
                "(478,'peso colombiano','7','0','0'),\n" +
                "(479,'peso filipino','7','0','0'),\n" +
                "(480,'peso mexicano','7','0','0'),\n" +
                "(481,'peso uruguaio','7','0','0'),\n" +
                "(482,'pula','7','0','0'),\n" +
                "(516,'quetzal','7','0','0'),\n" +
                "(483,'quiat','7','0','0'),\n" +
                "(484,'rand','7','0','0'),\n" +
                "(485,'real','7','0','0'),\n" +
                "(486,'rial','7','0','0'),\n" +
                "(487,'rial iemenita','7','0','0'),\n" +
                "(488,'riel','7','0','0'),\n" +
                "(489,'ringgit','7','0','0'),\n" +
                "(490,'riyal','7','0','0'),\n" +
                "(491,'rublo','7','0','0'),\n" +
                "(492,'rublo biélorrusso','7','0','0'),\n" +
                "(493,'Rupi Mauriciana','7','0','0'),\n" +
                "(494,'Rupia ','7','0','0'),\n" +
                "(495,'rúpia ceilandesa','7','0','0'),\n" +
                "(496,'rupia indiana','7','0','0'),\n" +
                "(497,'rupia indonésia','7','0','0'),\n" +
                "(498,'Rupia Maldivia','7','0','0'),\n" +
                "(499,'rúpia nepalesa','7','0','0'),\n" +
                "(500,'som','7','0','0'),\n" +
                "(501,'som uzbeque','7','0','0'),\n" +
                "(502,'somoni','7','0','0'),\n" +
                "(503,'taka','7','0','0'),\n" +
                "(504,'tala','7','0','0'),\n" +
                "(505,'tenge','7','0','0'),\n" +
                "(506,'togrog','7','0','0'),\n" +
                "(507,'Vatu','7','0','0'),\n" +
                "(508,'won','7','0','0'),\n" +
                "(509,'won norte coreano','7','0','0'),\n" +
                "(510,'xelim de Uganda','7','0','0'),\n" +
                "(511,'xelim queniano','7','0','0'),\n" +
                "(512,'xelim somali','7','0','0'),\n" +
                "(513,'xelim tanzaniano','7','0','0'),\n" +
                "(514,'yuan','7','0','0'),\n" +
                "(515,'zloty','7','0','0')");
    }



    private void popularTabelaParametros(SQLiteDatabase db) {
        db.execSQL("INSERT INTO `parametros` VALUES (1,1,1);\n");
    }
    private void popularTabelaJogoPendente(SQLiteDatabase db) {
        db.execSQL("INSERT INTO `jogopendente` VALUES (1,0);\n");
    }
    private void popularTabelaQuantidadePerguntas(SQLiteDatabase db) {
        db.execSQL("INSERT INTO `quantidadePerguntas` VALUES (1,15);\n");
    }
    private void popularTabelaSequenciaVitorias(SQLiteDatabase db) {
        db.execSQL("INSERT INTO `sequenciaVitorias` " +
                "VALUES " +
                "(1,'Geral',0,0)," +
                "(2,'Partidas de 15',0,0)," +
                "(3,'Partidas de 30',0,0)," +
                "(4,'Partidas de 50',0,0)\n");

    }
    private void popularTabelaConquistas(SQLiteDatabase db) {
        db.execSQL("INSERT INTO `conquistas` VALUES " +
                "(1,'Geografo Junior',50,0,0,6)," +
                "(2,'Geografo Pleno',120,0,0,6)," +
                "(3,'Geografo Senior',196,0,0,6)," +
                "(4,'Linguista Junior',50,0,0,3)," +
                "(5,'Linguista Pleno',120,0,0,3)," +
                "(6,'Linguista Senior',196,0,0,3)," +
                "(7,'Politico Junior',50,0,0,1),"+
                "(8,'Politico Pleno',120,0,0,1),"+
                "(9,'Politico Senior',196,0,0,1),"+
                "(10,'Vexilogista Junior',50,0,0,2),"+
                "(11,'Vexilogista Pleno',120,0,0,2),"+
                "(12,'Vexilogista Senior',196,0,0,2),"+
                "(13,'Demografo Junior',50,0,0,4),"+
                "(14,'Demografo Pleno',120,0,0,4),"+
                "(15,'Demografo Senior',196,0,0,4),"+
                "(16,'Cartografo Junior',50,0,0,9),"+
                "(17,'Cartografo Pleno',100,0,0,9),"+
                "(18,'Cartografo Senior',149,0,0,9),"+
                "(19,'Topógrafo Junior',50,0,0,5),"+
                "(20,'Topógrafo Pleno',120,0,0,5),"+
                "(21,'Topógrafo Senior',196,0,0,5),"+
                "(22,'Economista Junior',50,0,0,7),"+
                "(23,'Economista Pleno',120,0,0,7),"+
                "(24,'Economista Senior',196,0,0,7),"+
                "(25,'Heraldista Junior',50,0,0,8),"+
                "(26,'Heraldista Pleno',120,0,0,8),"+
                "(27,'Heraldista Senior',196,0,0,8),"+
                "(28,'Turista Junior',50,0,0,10),"+
                "(29,'Turista Pleno',80,0,0,10),"+
                "(30,'Turista Senior',112,0,0,10)");

    }
    private void popularTabelaConquistasSemCategoria(SQLiteDatabase db) {
        db.execSQL("INSERT INTO `conquistas_sem_categorias` VALUES " +
                "(1,'Vença 1 partida',1,0)," +
                "(2,'Vença 10 partidas',10,0)," +
                "(3,'Vença 50 partidas',50,0)," +
                "(4,'Vença 100 partidas',100,0)," +
                "(5,'Vença 200 partidas',200,0)," +
                "(6,'Vença 500 partidas',500,0),"+
                "(7,'Vença 1000 partidas',500,0),"+
                "(8,'Vença 10 partidas sem erros',10,0)," +
                "(9,'Vença 50 partidas sem erros',50,0)," +
                "(10,'Vença 100 partidas sem erros',100,0)," +
                "(11,'Vença 200 partidas sem erros',200,0),"+
                "(12,'Desbloqueie todas as categorias',10,0),"+
                "(13,'30 mil pontos em uma partida',30000,0),"+
                "(14,'50 mil pontos em uma partida',50000,0)," +
                "(15,'100 mil pontos em uma partida',100000,0)," +
                "(16,'500 mil pontos em uma partida',500000,0)," +
                "(17,'800 mil pontos em uma partida',800000,0),"+
                "(18,'1 milhao de pontos em uma partida',1000000,0),"+
                "(19,'Vença 5 partidas seguidas',5,0),"+
                "(20,'Vença 10 partidas seguidas',10,0),"+
                "(21,'Vença 50 partidas seguidas',50,0),"+
                "(22,'Vença 100 partidas seguidas',100,0),"+
                "(23,'Vença 1 partida com Todas Categorias Selecionadas',1,0),"+
                "(24,'Vença 10 partidas com Todas Categorias Selecionadas',10,0),"+
                "(25,'Vença 50 partidas Todas Categorias Selecionadas',50,0),"+
                "(26,'Vença 100 partidas Todas Categorias Selecionadas',100,0),"+
                "(27,'Vença 200 partidas Todas Categorias Selecionadas',200,0),"+
                "(28,'África',800,0),"+
                "(29,'América',600,0),"+
                "(30,'Ásia',700,0),"+
                "(31,'Europa',800,0),"+
                "(32,'Oceania',200,0)");

    }
    private void popularTabelaAcertosContinenes(SQLiteDatabase db) {
        db.execSQL("INSERT INTO acertosContinentes VALUES" +
                "(1,'África',0),\n" +
                "(2,'América',0),\n" +
                "(3,'Ásia',0),\n" +
                "(4,'Europa',0),\n" +
                "(5,'Oceania',0)");
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {



    }
}
