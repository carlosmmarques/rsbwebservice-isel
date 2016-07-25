-- GERAR DADOS DE eliminado

-- ALGORITMOS DE CALCULO DE TURNO:
INSERT INTO algoritmo_calculo_turno(id, descricao, designacao, servico_permanente, version) VALUES
  ('1', '12+24+12+48', 'Algoritmo Turno', TRUE, 0),
  ('2', 'Horário Normal', 'Algoritmo Horario Normal', FALSE, 0),
  ('3', 'Manhã / Tarde / Noite', 'Algoritmo 8 Hrs (M/T/N)', TRUE, 0),
  ('4', 'Sem Turno', 'Sem Turno', FALSE, 0)
ON CONFLICT DO NOTHING
;

-- PERIODOS DE CICLO DE TURNO:
INSERT INTO ciclo_turno (ordem_periodo_ciclo, num_horas, periodo_descanso, algoritmo_calculo_turno_id, version) VALUES
  ('1', '12', FALSE, '1', 0),
  ('2', '24', TRUE, '1', 0),
  ('3', '12', FALSE, '1', 0),
  ('4', '48', TRUE, '1', 0),
  ('1', '4', FALSE, '2', 0),
  ('2', '1', TRUE, '2', 0),
  ('3', '4', FALSE, '2', 0),
  ('4', '15', TRUE, '2', 0),
  ('1', '8', FALSE, '3', 0),
  ('2', '16', TRUE, '3', 0),
  ('3', '8', FALSE, '3', 0),
  ('4', '24', TRUE, '3', 0),
  ('5', '8', FALSE, '3', 0),
  ('6', '16', TRUE, '3', 0),
  ('7', '8', FALSE, '3', 0),
  ('8', '16', TRUE, '3', 0),
  ('9', '8', FALSE, '3', 0),
  ('10', '24', TRUE, '3', 0),
  ('11', '8', FALSE, '3', 0),
  ('12', '16', TRUE, '3', 0),
  ('13', '8', FALSE, '3', 0),
  ('14', '24', TRUE, '3', 0),
  ('15', '24', TRUE, '4', 0)
ON CONFLICT DO NOTHING
;

-- TURNOS
INSERT INTO turno (id, designacao, dt_inicio_ciclo, hr_inicio_ciclo, algoritmo_calculo_turno_id, version) VALUES
  ('1', '1', '29-11-2015', '08:00', '1', 0),
  ('2', '2', '30-11-2015', '08:00', '1', 0),
  ('3', '3', '01-12-2015', '08:00', '1', 0),
  ('4', '4', '02-12-2015', '08:00', '1', 0),
  ('5', 'Horário Normal', '01-12-2015', '08:00', '2', 0),
  ('6', '5 (8 horas)', '01-12-2015', '08:00', '3', 0),
  ('7', '6 (8 horas)', '01-12-2015', '16:00', '3', 0),
  ('8', '7 (8 horas)', '01-12-2015', '00:00', '3', 0),
  ('9', 'Sem Turno', '01-12-2015', '00:00', '4', 0)
ON CONFLICT DO NOTHING
;

-- POSTOS FUNCIONAIS
INSERT INTO posto_funcional(id, descricao, designacao, version) VALUES
  ('1', 'Comandante', 'Cmdt', 0),
  ('2', '2º comandante', '2ºCmdt', 0),
  ('3', 'Comandante de Companhia', 'Cmdt Comp.', 0),
  ('4', 'Adj.Tec', 'Adjunto Técnico', 0),
  ('5', 'Bombeiro Sapador', 'B. Sap.', 0),
  ('6', 'Chefe Principal', 'Chefe', 0),
  ('7', 'Sub Chefe 1ª', 'S/Ch 1ª', 0),
  ('8', 'Sub Chefe 1ª Classe', 'S/Ch 1ª Cl', 0),
  ('9', 'Sub Chefe 1ª Classe (Regime Mobilidade)', 'S/Ch 1ª RM', 0),
  ('10', 'Sub Chefe 2ª', 'S/Ch 2ª', 0),
  ('11', 'Sub Chefe 2ª Classe', 'S/Ch 2ª Cl', 0),
  ('12', 'Sub Chefe Principal', 'S/Ch Pr.', 0),
  ('13', 'Sub Chefe Principal (Regime Mobilidade)', 'S/Ch Pr. RM', 0),
  ('14', 'Bombeiro Sapador Recruta', 'B. Rec.', 0),
  ('15', 'Sem posto por omissão', 'Sem Posto', 0)
ON CONFLICT DO NOTHING
;

-- TIPOS DE PRESENÇA
INSERT INTO tipo_presenca(id, abreviatura, ausencia, descricao, reforco, version) VALUES
  ('A', 'Doença', TRUE, 'Situação de Doente', FALSE, 0),
  ('B', 'Ac. Serv', TRUE, 'Acidente em Servico', FALSE, 0),
  ('C', 'Casamento', TRUE, 'Dispensa de Casamento', FALSE, 0),
  ('D', 'Nojo', TRUE, 'Dispensa de Nojo', FALSE, 0),
  ('E', 'Ass. Fam.', TRUE, 'Ass. Fam. Menores', FALSE, 0),
  ('SG', 'Act. Sind.', TRUE, 'Actividade Sindical', FALSE, 0),
  ('H', 'F. Injust.', TRUE, 'Falta injustificada', FALSE, 0),
  ('I', 'Lic. Patern.', TRUE, 'Licença de Paternidade', FALSE, 0),
  ('J', 'Inact. Quadro', TRUE, 'Inactividade Quadro', FALSE, 0),
  ('F', 'Ass. Familia', TRUE, 'Ass. Familia', FALSE, 0),
  ('L', 'Lic. Parental', TRUE, 'Licença Parental', FALSE, 0),
  ('M', 'Lic. Nasc.', TRUE, 'Lic. Nascimento', FALSE, 0),
  ('N', 'Hospital', TRUE, 'Hospital', FALSE, 0),
  ('X', 'Ferias Confirmadas', TRUE, 'Ferias Confirmadas', FALSE, 0),
  ('Z', 'Ferias N/ Confirm.', TRUE, 'Ferias N/ Confirm.', FALSE, 0),
  ('T', 'Transferência', TRUE, 'Transferência para outra Unidade', FALSE, 0),
  ('0', 'N/A', TRUE, 'Não aplicável', FALSE, 0),
  ('S', 'Serviço Interno', FALSE, 'Serviços Internos Comandante Companhia / Batalhão / Secção', FALSE, 0),
  ('S0', 'Disp. Compensação', TRUE, 'Dispensa por compensação', FALSE, 0),
  ('1S', 'CPO / CH CIDADE  (Reforço)', FALSE, 'Comandante de Permanencia às Operações / Chefe de Serviço à Cidade', TRUE, 0),
  ('2S', 'Ch. 1ª Interv.  (Reforço)', FALSE, 'Chefe de 1ª Intervenção', TRUE, 0),
  ('3S', 'S/chefes  (Reforço)', FALSE, 'Sub Chefes', TRUE, 0),
  ('4S', 'Linha (Reforço)', FALSE, 'Pessoal de Linha', TRUE, 0),
  ('5S', 'Motoristas (Reforço)', FALSE, 'Motoristas', TRUE, 0),
  ('6S', 'Mg/Mac/Nisac  (Reforço)', FALSE, 'Mergulhadores / Maqueiros / Apoio Especializado', TRUE, 0),
  ('9S', 'Formaçãpo  (Reforço)', FALSE, 'FAORMAÇÃO', TRUE, 0)
ON CONFLICT DO NOTHING
;
INSERT INTO tipo_presenca(id, abreviatura, ausencia, descricao, reforco, tipo_presenca_em_reforco_id, version) VALUES
  ('S1', 'CPO / CH CIDADE', FALSE, 'Comandante de Permanencia às Operações / Chefe de Serviço à Cidade', FALSE, '1S', 0),
  ('S2', 'Ch. 1ª Interv.', FALSE, 'Chefe de 1ª Intervenção', FALSE, '2S', 0),
  ('S3', 'S/chefes', FALSE, 'Sub Chefes', FALSE, '3S', 0),
  ('S4', 'Linha', FALSE, 'Pessoal de Linha', FALSE, '4S', 0),
  ('S5', 'Motoristas', FALSE, 'Motoristas', FALSE, '5S', 0),
  ('S6', 'Mg/Mac/Nisac', FALSE, 'Mergulhadores / Maqueiros / Apoio Especializado', FALSE, '6S', 0),
  ('S9', 'Formação', FALSE, 'Formação', FALSE, '9S', 0)
ON CONFLICT DO NOTHING
;

-- TIPOS DE UNIDADES ESTRUTURAIS
INSERT INTO tipo_unidade_estrutural(id, descricao, designacao, nivel_hierarquico_maximo_mae, version) VALUES
  ('1', 'unidade composta por dois ou mais batalhões', 'Regimento', '0', 0),
  ('2', 'subdivisão de um regimento, formada por um número determinado de companhias', 'Batalhão', '1', 0),
  ('3', 'subunidade de nível inferior ao batalhão', 'Companhia', '2', 0),
  ('4', 'subunidade de nível inferior à companhia', 'Secção', '3', 0)
ON CONFLICT DO NOTHING
;

-- UNIDADES ESTRUTURAIS
INSERT INTO unidade_estrutural(id, designacao, nivel_hierarquico, tipo_unidade_estrutural_id, version) VALUES
  ('1','Regimento Sapadores Bombeiros','1','1', 0)
ON CONFLICT DO NOTHING
;
INSERT INTO unidade_estrutural(id, designacao, nivel_hierarquico, tipo_unidade_estrutural_id, unidade_estrutural_mae_id, version) VALUES
  ('2','2º Batalhão','2','2','1', 0),
  ('3','3ª Companhia','3','3','2', 0)
ON CONFLICT DO NOTHING
;

-- INSTALAÇÕES
INSERT INTO instalacao (id, descricao, designacao, localizacao, unidade_estrutural_id, version) VALUES
  ('1', 'Quartel - Sede', 'S', 'Av. Rio de Janeiro', '3', 0),
  ('2', 'Quartel - Externo', 'E', 'Colombo', '3', 0)
ON CONFLICT DO NOTHING
;

-- FORMAÇÃO
INSERT INTO formacao(id, descricao, designacao, validade, version) VALUES
  ('1', 'Carta Condução +3500Kg', 'Condução de Pesados', '20', 0),
  ('2', 'Curso Mecanica geral', 'Mecânica geral', '-1', 0),
  ('3', 'Torneiro mecancico', 'Torneiro', '-1', 0),
  ('4', 'Curso de cozinha', 'Cozinheiro', '-1', 0),
  ('5', 'Não disponível', 'N/D', '-1', 0)
ON CONFLICT DO NOTHING
;

-- TIPOS DE UNIDADES OPERACIONAIS
INSERT INTO  tipo_unidade_operacional(id, descricao, designacao, version) VALUES
  ('1', 'VLCI', '', 0),
  ('2', 'VUCI', '', 0),
  ('3', 'VE', '', 0),
  ('4', 'VTTU', '', 0),
  ('5', 'VOPE', '', 0),
  ('6', 'VCOT', '', 0),
  ('7', 'OPDAE', '', 0),
  ('8', 'COZINHA', '', 0),
  ('9', 'BAR', '', 0),
  ('10', 'PLANTÃO GARAGEM', '', 0)
ON CONFLICT DO NOTHING
;

-- PESSOAL
INSERT INTO elemento (id, matricula, abreviatura, data_nascimento, e_mail, factor_elegibilidade, id_interno, nif, nome, num_doc_identificacao, num_mecanografico, telefone1, telefone2, tipo_doc_identificacao, instalacao_id, posto_funcional_id, tipo_presenca_id, turno_id, version) VALUES
  ('1', '4193', '', '22-12-1961', 'jose.vieira@cm-lisboa.pt', '0', '14', '149302789', 'José António do Vale Vieira', '6056194', '435030', '910282666', '', 'BilheteIdentidade', '1', '13', 'S2', '2', 0),
  ('2', '4180', '', '02-11-1966', 'joaquim.choucho@cm-lisboa.pt', '0', '22', '189656085', 'Joaquim Lopes Choucho', '7608825', '408030', '966514925', '', 'BilheteIdentidade', '1', '13', 'S2', '1', 0),
  ('3', '4189', '', '11-07-1965', 'carlos.jose.vaz@cm-lisboa.pt', '0', '29', '187920648', 'Carlos José Taborda Vaz', '6995061', '203880', '919019546', '', 'BilheteIdentidade', '2', '7', 'S3', '9', 0),
  ('4', '4166', '', '17-04-1966', 'manuel.aleixo@cm-lisboa.pt', '0', '37', '191697915', 'Manuel Martins Aleixo', '7351206', '621000', '965245641', '', 'BilheteIdentidade', '1', '13', 'S2', '4', 0),
  ('5', '4188', '', '19-04-1964', 'paulo.guerreiro@cm-lisboa.pt', '0', '43', '180893890', 'Paulo Jorge de O.a Guerreiro', '6520046', '773500', '933130495', '', 'BilheteIdentidade', '1', '13', 'S2', '3', 0),
  ('6', '4182', '', '28-05-1966', 'victor.santos@cm-lisboa.pt', '0', '46', '174863378', 'Vítor Manuel Prelhaz Santos', '7356378', '814601', '965576774', '', 'BilheteIdentidade', '2', '8', 'S3', '1', 0),
  ('7', '4200', '', '19-02-1965', 'fernando.jorge.pinto@cm-lisboa.pt', '0', '55', '119352770', 'Fernando Jorge Pinto', '7036313', '276730', '910785662', '', 'BilheteIdentidade', '1', '13', 'S2', '2', 0),
  ('8', '4394', '', '28-12-1972', 'fernando.m.goncalves@cm-lisboa.pt', '0', '113', '205520170', 'Fernando M.C. Gonçalves', '10065064', '278833', '965703103', '', 'BilheteIdentidade', '1', '9', 'S5', '3', 0),
  ('9', '4310', '', '16-03-1970', 'jorge.capinha@cm-lisboa.pt', '0', '124', '194129004', 'Jorge Manuel B. Capinha', '9394285', '424710', '962469810', '', 'BilheteIdentidade', '1', '9', 'S3', '2', 0),
  ('10', '4181', '', '17-07-1966', 'pedro.manuel.rosa@cm-lisboa.pt', '0', '130', '154066141', 'Pedro Manuel Vital P. Rosa', '77360550', '775380', '964053601', '', 'BilheteIdentidade', '1', '11', 'S3', '2', 0),
  ('11', '4452', '', '25-01-1974', 'jose.manuel.marques@cm-lisboa.pt', '0', '152', '207346933', 'José Manuel F. Marques', '10180874', '950072', '966878719', '', 'BilheteIdentidade', '1', '9', 'S3', '3', 0),
  ('12', '4342', '', '22-09-1975', 'manuel.f.ribeiro@cm-lisboa.pt', '0', '173', '210882751', 'Manuel Fernando Costa Ribeiro', '10580747', '600090', '919121843', '', 'BilheteIdentidade', '1', '15', '0', '9', 0),
  ('13', '4293', '', '29-04-1969', 'sergio.branco@cm-lisboa.pt', '0', '201', '195273052', 'Sérgio Ribeiro Luciano Branco', '8556727', '800460', '931606935', '', 'BilheteIdentidade', '1', '9', 'S5', '4', 0),
  ('14', '4382', '', '11-06-1973', 'luis.serrano@cm-lisboa.pt', '0', '211', '210195959', 'Luis Miguel Lopes Serrano', '10036616', '564348', '962349837', '', 'BilheteIdentidade', '1', '9', 'S3', '4', 0),
  ('15', '4362', '', '14-10-1968', 'jose.freitas@cm-lisboa.pt', '0', '228', '191638714', 'José Luis Soares de Freitas', '96243930', '492205', '962350862', '', 'BilheteIdentidade', '2', '11', 'S3', '1', 0),
  ('16', '4272', '', '07-05-1972', 'carlos.jorge.santos@cm-lisboa.pt', '0', '235', '192082531', 'Carlos Jorge Costa Santos', '9868384', '203418', '962475547', '', 'BilheteIdentidade', '1', '9', 'S5', '2', 0),
  ('17', '4405', '', '05-02-1971', 'edgar.ministro@cm-lisboa.pt', '0', '239', '195725220', 'Edgar Emanuel Soares Ministro', '9700313', '240940', '913482188', '', 'BilheteIdentidade', '2', '15', '0', '9', 0),
  ('18', '4400', '', '17-10-1973', 'luis.leite@cm-lisboa.pt', '0', '241', '206680961', 'Luis Miguel Leite Oliveira', '10031787', '564343', '965249975', '', 'BilheteIdentidade', '2', '15', '0', '9', 0),
  ('19', '4428', '', '02-03-1977', 'joao.pepe@cm-lisboa.pt', '0', '246', '223001236', 'João Paulo Domingues Pepe', '11046009', '950078', '966867945', '', 'BilheteIdentidade', '2', '9', 'S3', '2', 0),
  ('20', '4276', '', '14-12-1972', 'jose.branco@cm-lisboa.pt', '0', '247', '182225216', 'José Manuel Cda Costa Branco', '10048172', '492235', '965024618', '', 'BilheteIdentidade', '2', '9', 'S3', '4', 0),
  ('21', '4332', '', '03-10-1973', 'pedro.amaral.melo@cm-lisboa.pt', '0', '248', '209656460', 'Pedro Amaral Melo', '11351990', '774195', '966853817', '', 'BilheteIdentidade', '1', '9', 'S3', '3', 0),
  ('23', '4186', '', '05-08-1967', 'carlos.m.martins@cm-lisboa.pt', '0', '258', '185962696', 'Carlos Manuel Ferreira Martins', '7819033', '204551', '962789226', '', 'BilheteIdentidade', '2', '11', 'S5', '1', 0),
  ('24', '4241', '', '02-01-1971', 'aderito.fernandes@cm-lisboa.pt', '0', '270', '200283154', 'Aderito Paulo de S Fernandes', '9588533', '13910', '963582731', '', 'BilheteIdentidade', '2', '11', 'S3', '3', 0),
  ('25', '4299', '', '24-02-1974', 'carlos.manuel.cruz@cm-lisboa.pt', '0', '276', '203245881', 'Carlos Manuel P. R. da Cruz', '10335442', '205290', '966338476', '', 'BilheteIdentidade', '1', '9', 'S3', '2', 0),
  ('26', '4255', '', '11-12-1971', 'paulo.pica@cm-lisboa.pt', '0', '281', '204441706', 'Paulo Alexandre Ramos Pica', '9916362', '772470', '966708847', '', 'BilheteIdentidade', '1', '9', 'S3', '2', 0),
  ('27', '4325', '', '06-10-1970', 'jose.joao.amaral@cm-lisboa.pt', '0', '285', '196307740', 'José João Gomes L. G. Amaral', '8962557', '482730', '917889934', '', 'BilheteIdentidade', '2', '9', 'S3', '4', 0),
  ('28', '4441', '', '05-03-1977', 'pedro.miguel.marques@cm-lisboa.pt', '0', '289', '211530840', 'Pedro Miguel Bernardo Marques', '11012403', '950109', '965282577', '', 'BilheteIdentidade', '2', '10', 'S5', '9', 0),
  ('29', '4314', '', '13-07-1970', 'carlos.espiga@cm-lisboa.pt', '0', '293', '187292817', 'Carlos Alberto Espiga', '9949782', '196320', '910940618', '', 'BilheteIdentidade', '1', '15', '0', '9', 0),
  ('30', '4373', '', '05-07-1972', 'delfina.almeida@cm-lisboa.pt', '0', '299', '205522718', 'Delfina Maria João Almeida', '10690507', '227200', '967124936', '', 'BilheteIdentidade', '1', '9', 'S5', '1', 0),
  ('31', '4352', '', '28-08-1972', 'jorge.pedro@cm-lisboa.pt', '0', '301', '191736562', 'Jorge Vicente da C. Pedro', '10120170', '427157', '960394257', '', 'BilheteIdentidade', '1', '11', 'S4', '3', 0),
  ('32', '4249', '', '08-01-1974', 'paulo.morais@cm-lisboa.pt', '0', '308', '210148815', 'Paulo Artur C. Dias de Morais', '10266946', '772551', '', '', 'BilheteIdentidade', '1', '11', 'S3', '1', 0),
  ('33', '4318', '', '31-10-1969', 'joaquim.m.mendes@cm-lisboa.pt', '0', '309', '192049852', 'Joaquim Manuel Correia Mendes', '9322481', '409840', '966657906', '', 'BilheteIdentidade', '1', '11', 'S5', '3', 0),
  ('34', '4271', '', '02-08-1970', 'luis.teixeira@cm-lisboa.pt', '0', '311', '189138092', 'Luis António da Cruz Teixeira', '9565762', '554970', '917168044', '', 'BilheteIdentidade', '1', '9', 'S3', '3', 0),
  ('35', '4827', '', '09-06-1988', 'hugo.santos.nunes@cm-lisboa.pt', '0', '358', '254737943', 'Hugo Alexandre dos S. Nunes', '13336296', '955531', '915914276', '', 'BilheteIdentidade', '1', '5', 'S4', '1', 0),
  ('36', '4808', '', '05-02-1971', 'fabio.alves@cm-lisboa.pt', '0', '381', '195725220', 'Fábio André da Silva Alves', '9700313', '955514', '918431195', '', 'BilheteIdentidade', '1', '5', 'S4', '3', 0),
  ('37', '4793', '', '18-12-1989', 'ruben.reis@cm-lisboa.pt', '0', '385', '263806073', 'Rúben José Rodrigues Reis', '13633154', '955597', '914726040', '', 'BilheteIdentidade', '1', '5', 'S4', '4', 0),
  ('38', '4860', '', '06-04-1987', 'tiago.b.marques@cm-lisboa.pt', '0', '388', '242022235', 'Tiago Bernardo Marques', '13178358', '955609', '912326478', '', 'BilheteIdentidade', '1', '5', 'S4', '4', 0),
  ('39', '4922', '', '23-10-1985', 'paulo.martins@cm-lisboa.pt', '0', '389', '221628916', 'Paulo Alexandre Alves Martins', '12876634', '955578', '', '', 'BilheteIdentidade', '1', '5', 'S4', '2', 0),
  ('40', '4809', '', '08-11-1990', 'andre.goncalves@cm-lisboa.pt', '0', '392', '205060951', 'André da Costa Gonçalves', '13752102', '955482', '927376969', '', 'BilheteIdentidade', '1', '5', 'S4', '3', 0),
  ('41', '4907', '', '21-06-1988', 'vasco.silva@cm-lisboa.pt', '0', '402', '245397671', 'Vasco Gonçalo F. da Silva', '13252880', '955621', '969590648', '', 'BilheteIdentidade', '1', '5', 'S4', '1', 0),
  ('42', '4854', '', '13-05-1987', 'rui.pratas@cm-lisboa.pt', '0', '404', '251120554', 'Rui Fernando Aires Pratas', '13561108', '955599', '962166781', '', 'BilheteIdentidade', '1', '5', 'S5', '3', 0),
  ('43', '4786', '', '24-12-1990', 'andre.rodrigues@cm-lisboa.pt', '0', '441', '262993252', 'André Pereira da Cruz Rodrigues', '13807638', '955492', '912521849', '', 'BilheteIdentidade', '1', '5', 'S4', '3', 0),
  ('44', '4863', '', '22-07-1989', 'lino.alves@cm-lisboa.pt', '0', '445', '244933235', 'Lino Miguel Alves', '13637961', '955551', '919869569', '', 'BilheteIdentidade', '1', '5', 'S5', '4', 0),
  ('45', '4915', '', '02-07-1986', 'filipe.carreira@cm-lisboa.pt', '0', '450', '231333579', 'Filipe José Torres Carreira', '13076598', '955520', '964776061', '', 'BilheteIdentidade', '1', '5', 'S4', '3', 0),
  ('46', '4564', '', '28-01-1979', 'joao.paulo.mendes@cm-lisboa.pt', '0', '452', '218623208', 'João Paulo Vaz Mendes', '11613675', '951649', '965586075', '', 'BilheteIdentidade', '1', '5', 'S4', '1', 0),
  ('47', '4617', '', '09-11-1979', 'nuno.varela@cm-lisboa.pt', '0', '454', '212833294', 'Nuno Miguel Monteiro Varela', '11441591', '952198', '934456007', '', 'BilheteIdentidade', '1', '5', 'S4', '2', 0),
  ('48', '4616', '', '24-02-1982', 'valter.fonseca@cm-lisboa.pt', '0', '456', '210341904', 'Valter Miguel Oliveira Fonseca', '12110470', '952189', '962267314', '', 'BilheteIdentidade', '1', '5', 'S5', '4', 0),
  ('49', '4929', '', '12-06-1985', 'alexandre.m.pinto@cm-lisboa.pt', '0', '461', '238138950', 'Alexandre Miguel Sousa Pinto', '12725918', '955478', '918714908', '', 'BilheteIdentidade', '1', '15', 'S5', '1', 0),
  ('50', '4304', '', '19-04-1972', 'daniel.xavier@cm-lisboa.pt', '0', '463', '204542871', 'Daniel Marcelino Xavier', '10108827', '224300', '961381678', '', 'BilheteIdentidade', '1', '5', 'S4', '3', 0),
  ('51', '4810', '', '16-10-1988', 'miguel.braz@cm-lisboa.pt', '0', '468', '252466144', 'Miguel Ângelo Taborda Bráz', '13372577', '955569', '926426976', '', 'BilheteIdentidade', '1', '5', 'S4', '2', 0),
  ('52', '4884', '', '20-02-1985', 'paulo.garcias@cm-lisboa.pt', '0', '469', '243397496', 'Paulo Jorge Inácio Garcias', '12826954', '955581', '966510284', '', 'BilheteIdentidade', '1', '5', 'S4', '2', 0),
  ('53', '4649', '', '15-11-1977', 'tiago.matias@cm-lisboa.pt', '0', '492', '', 'Tiago Alexandre M. Matias', '', '952237', '', '', 'BilheteIdentidade', '1', '15', '0', '9', 0),
  ('54', '4919', '', '01-04-1986', 'bruno.barroso@cm-lisboa.pt', '0', '503', '253614279', 'Bruno Gee Barroso', '13541525', '955495', '914416026', '', 'BilheteIdentidade', '2', '5', 'S4', '3', 0),
  ('55', '4794', '', '10-11-1988', 'tiago.venturinha@cm-lisboa.pt', '0', '510', '257918701', 'Tiago Frederico L. P. Venturinha', '13451674', '955615', '913683769', '', 'BilheteIdentidade', '1', '5', 'S4', '1', 0),
  ('56', '4657', '', '30-11-1982', 'susana.pires@cm-lisboa.pt', '0', '517', '221367250', 'Susana Frederica da S. M. Pires', '12018662', '952234', '938526319', '', 'BilheteIdentidade', '1', '5', 'S4', '4', 0),
  ('57', '4943', '', '08-09-1986', 'nelson.crucho@cm-lisboa.pt', '0', '519', '205154867', 'Nelson Filipe Campos Crucho', '12712879', '955632', '967503526', '', 'BilheteIdentidade', '1', '5', 'S4', '3', 0),
  ('58', '4874', '', '10-03-1988', 'fabio.sequeira@cm-lisboa.pt', '0', '532', '247762032', 'Fábio Miguel Capinha Sequeira', '13378156', '955517', '963779761', '', 'BilheteIdentidade', '2', '5', 'S4', '2', 0),
  ('59', '4627', '', '23-01-1981', 'rogerio.beatriz@cm-lisboa.pt', '0', '548', '209648740', 'Rogério Mendonça Beatriz', '11914264', '952224', '919007589', '', 'BilheteIdentidade', '1', '5', 'S4', '1', 0),
  ('60', '4704', '', '01-01-1978', 'manuel.monteiro@cm-lisboa.pt', '0', '555', '219744963', 'Manuel Pereira Monteiro', '11336388', '953653', '964612845', '', 'BilheteIdentidade', '1', '5', 'S4', '4', 0),
  ('61', '4633', '', '27-11-1979', 'bruno.mourinha@cm-lisboa.pt', '0', '561', '221306668', 'Bruno Miguel Entidade. F. Mourinha', '11521634', '952160', '962873662', '', 'BilheteIdentidade', '2', '5', 'S5', '3', 0),
  ('62', '4871', '', '19-04-1987', 'emanuel.alves@cm-lisboa.pt', '0', '567', '217610145', 'Emanuel da Silva Alves', '13333184', '955511', '919959030', '', 'BilheteIdentidade', '2', '5', 'S4', '4', 0),
  ('63', '4837', '', '01-11-1985', 'rui.pacheco@cm-lisboa.pt', '0', '571', '241791944', 'Rui Pedro Ribeiro Pacheco', '12869065', '955603', '962330521', '', 'BilheteIdentidade', '2', '5', 'S4', '2', 0),
  ('64', '4641', '', '18-10-1978', 'marco.marques@cm-lisboa.pt', '0', '573', '215015282', 'Marco António Pereira Marques', '11498676', '952187', '0', '', 'BilheteIdentidade', '1', '5', 'S4', '4', 0),
  ('65', '4868', '', '18-08-1985', 'nelson.ferreira@cm-lisboa.pt', '0', '576', '245788662', 'Nelson Manuel Sousa Ferreira', '12706112', '955575', '919424406', '', 'BilheteIdentidade', '1', '5', 'S4', '2', 0),
  ('66', '4612', '', '10-03-1980', 'jose.f.correia@cm-lisboa.pt', '0', '577', '210145099', 'José Manuel Ferreira Correia', '11882181', '952184', '962704543', '', 'BilheteIdentidade', '1', '5', 'S5', '2', 0),
  ('67', '4618', '', '23-07-1978', 'alexandre.silva@cm-lisboa.pt', '0', '590', '217529380', 'Alexandre José Correia da Silva', '11268936', '952152', '962381806', '', 'BilheteIdentidade', '1', '5', 'S4', '2', 0),
  ('68', '4873', '', '21-08-1986', 'mauro.barbosa@cm-lisboa.pt', '0', '594', '229880533', 'Mauro Alexandre Pires Barbosa', '13006690', '955567', '927464772', '', 'BilheteIdentidade', '1', '5', 'S4', '1', 0),
  ('69', '4936', '', '13-11-1988', 'paulo.gaspar@cm-lisboa.pt', '0', '595', '221179739', 'Paulo Ricardo Martins Gaspar', '13579643', '955625', '963186260', '', 'BilheteIdentidade', '1', '5', 'S4', '1', 0),
  ('70', '4585', '', '17-04-1978', 'luis.vaz.rodrigues@cm-lisboa.pt', '0', '607', '223250996', 'Luis Manuel Vaz Rodrigues', '11259981', '951656', '961805814', '', 'BilheteIdentidade', '1', '15', '0', '9', 0),
  ('71', '4646', '', '16-11-1977', 'pedro.esteves@cm-lisboa.pt', '0', '613', '215556089', 'Pedro António Moita Esteves', '11031547', '952203', '966333633', '', 'BilheteIdentidade', '2', '5', 'S4', '1', 0),
  ('72', '4634', '', '10-12-1978', 'nelson.rosa@cm-lisboa.pt', '0', '615', '208870709', 'Nelson Conceição Rosa', '11302662', '952193', '914081150', '', 'BilheteIdentidade', '2', '5', 'S5', '4', 0),
  ('73', '4630', '', '05-08-1979', 'pedro.duarte@cm-lisboa.pt', '0', '616', '965355910', 'Pedro Nuno Duarte', '11494331', '952213', '964276783', '', 'BilheteIdentidade', '2', '5', 'S4', '2', 0),
  ('74', '4917', '', '29-09-1985', 'tiago.marques@cm-lisboa.pt', '0', '620', '234816210', 'Tiago Filipe da Silva Marques', '13285249', '955610', '961130707', '', 'BilheteIdentidade', '1', '5', 'S4', '3', 0),
  ('75', '4668', '', '12-12-1983', 'rogerio.rodrigues@cm-lisboa.pt', '0', '647', '232258651', 'Rogério Manuel Luzio Rodrigues', '12394562', '952223', '969447811', '', 'BilheteIdentidade', '2', '5', 'S4', '2', 0),
  ('76', '4686', '', '04-09-1982', 'goncalo.robalo@cm-lisboa.pt', '0', '651', '189973979', 'Gonçalo José Lopes Robalo', '12189563', '952171', '965130469', '', 'BilheteIdentidade', '2', '15', '0', '9', 0),
  ('77', '4666', '', '12-07-1977', 'humberto.freire@cm-lisboa.pt', '0', '652', '219176191', 'Humberto Elísio R. Reis Freire', '11117238', '952177', '966354992', '', 'BilheteIdentidade', '1', '5', 'S5', '1', 0),
  ('78', '4680', '', '09-11-1983', 'goncalo.cruz@cm-lisboa.pt', '0', '662', '239595440', 'Gonçalo Prazeres R.da Cruz', '12369861', '952173', '966338476', '', 'BilheteIdentidade', '1', '5', 'S4', '4', 0),
  ('79', '4880', '', '09-07-1986', 'andre.fernandes@cm-lisboa.pt', '0', '666', '253695600', 'André Filipe Pereira Fernandes', '12912006', '955490', '962640938', '', 'BilheteIdentidade', '2', '5', 'S4', '1', 0),
  ('80', '4750', '', '20-03-1980', 'pedro.batista@cm-lisboa.pt', '0', '675', '228085543', 'Pedro Miguel Barreira Batista', '11731762', '953699', '964251266', '', 'BilheteIdentidade', '1', '5', 'S4', '3', 0),
  ('81', '4670', '', '11-01-1983', 'rui.carvalho@cm-lisboa.pt', '0', '679', '217893430', 'Rui Filipe Barbosa Carvalho', '12391181', '952226', '918890122', '', 'BilheteIdentidade', '2', '15', '0', '9', 0),
  ('82', '4664', '', '03-10-1980', 'joao.paulo.cunha@cm-lisboa.pt', '0', '680', '228586968', 'João Paulo Duarte da Cunha', '11723408', '952182', '969608238', '', 'BilheteIdentidade', '2', '5', 'S4', '4', 0),
  ('83', '4682', '', '02-03-1979', 'cristiano.fidalgo@cm-lisboa.pt', '0', '683', '224404210', 'Cristiano Maria Fidalgo', '11999526', '952166', '965248087', '', 'BilheteIdentidade', '1', '15', '0', '9', 0),
  ('84', '4623', '', '06-12-1983', 'iuri.gomes@cm-lisboa.pt', '0', '692', '238536041', 'Iuri Timótio da Silva Gomes', '12411376', '952178', '937239626', '', 'BilheteIdentidade', '2', '5', 'S4', '4', 0),
  ('85', '4652', '', '14-03-1977', 'paulo.simoes.silva@cm-lisboa.pt', '0', '695', '208764011', 'Paulo Alexandre Simões da Silva', '11628437', '952200', '965541746', '', 'BilheteIdentidade', '1', '5', 'S5', '2', 0),
  ('86', '4759', '', '02-11-1980', 'nuno.dias@cm-lisboa.pt', '0', '702', '207863474', 'Nuno Brito Cardoso Dias', '12433582', '953708', '918652909', '', 'BilheteIdentidade', '2', '5', 'S4', '1', 0),
  ('87', '4744', '', '26-10-1981', 'fernando.reis@cm-lisboa.pt', '0', '705', '230517129', 'Fernando Manuel Pires dos Reis', '11955522', '953693', '966100374', '', 'BilheteIdentidade', '2', '5', 'S4', '3', 0),
  ('88', '4770', '', '17-02-1979', 'antonino.dias@cm-lisboa.pt', '0', '720', '219933820', 'Antonino Pereira Dias', '11527635', '953719', '964877531', '', 'BilheteIdentidade', '1', '5', 'S4', '3', 0),
  ('89', '4335', '', '14-08-1971', 'jose.manuel.pinheiro@cm-lisboa.pt', '0', '742', '196101824', 'José Manuel dos S. Pinheiro', '9884835', '496646', '933210971', '', 'BilheteIdentidade', '2', '15', '0', '9', 0),
  ('90', '4778', '', '29-07-1978', 'miguel.monteiro@cm-lisboa.pt', '0', '761', '223347230', 'Miguel Henrique Sousa Monteiro', '1154168', '953727', '962465385', '', 'BilheteIdentidade', '1', '15', '0', '9', 0),
  ('91', '4720', '', '28-12-1982', 'luciano.costa@cm-lisboa.pt', '0', '763', '228314143', 'Luciano Manuel Martins Costa', '12104206', '953669', '967975530', '', 'BilheteIdentidade', '1', '5', 'S4', '2', 0),
  ('92', '4739', '', '16-01-1982', 'bruno.costa@cm-lisboa.pt', '0', '773', '226539156', 'Bruno Carvalheira Costa', '12149696', '953688', '933764199', '', 'BilheteIdentidade', '2', '5', 'S4', '3', 0),
  ('93', '4749', '', '09-11-1981', 'ricardo.alaiz@cm-lisboa.pt', '0', '777', '229575374', 'Ricardo Jorge Alaiz Ribeiro', '11925471', '953698', '212940678', '', 'BilheteIdentidade', '1', '5', 'S4', '2', 0),
  ('94', '4776', '', '05-03-1983', 'jorge.miguel.santos@cm-lisboa.pt', '0', '789', '228677793', 'Jorge Miguel Caixas dos Santos', '12565807', '953725', '916537068', '', 'BilheteIdentidade', '2', '5', 'S4', '1', 0),
  ('95', '4719', '', '24-02-1984', 'nuno.miguel.cordeiro@cm-lisboa.pt', '0', '796', '242584667', 'Nuno Miguel Cordeiro Rodrigues', '12517915', '953668', '213855265', '', 'BilheteIdentidade', '2', '5', 'S4', '1', 0),
  ('96', '4775', '', '09-08-1982', 'bruno.teixeira@cm-lisboa.pt', '0', '797', '232124370', 'Bruno Miguel dos S. Teixeira', '12398488', '953724', '965756601', '', 'BilheteIdentidade', '1', '5', 'S4', '3', 0),
  ('97', '4747', '', '13-04-1978', 'carlos.s.rodrigues@cm-lisboa.pt', '0', '805', '222487437', 'Carlos António dos S.Rodrigues', '11304971', '953696', '933373815', '', 'BilheteIdentidade', '1', '5', 'S4', '1', 0),
  ('98', '4708', '', '26-11-1979', 'tiago.rodrigues@cm-lisboa.pt', '0', '809', '220849897', 'Tiago Filipe Lopes Rodrigues', '1150035', '953657', '966502375', '', 'BilheteIdentidade', '1', '5', 'S4', '1', 0),
  ('99', '4738', '', '29-04-1982', 'joao.pedro.carvalho@cm-lisboa.pt', '0', '811', '233165819', 'João Pedro P.de Carvalho', '12174168', '953687', '965136970', '', 'BilheteIdentidade', '1', '5', 'S4', '1', 0),
  ('100', '4355', '', '07-01-1973', 'luis.azinheiro@cm-lisboa.pt', '0', '824', '196107156', 'Luis Miguel Torres Azinheiro', '10091623', '564380', '924007513', '', 'BilheteIdentidade', '1', '5', 'S4', '2', 0),
  ('101', '4377', '', '07-11-1970', 'mario.tavares@cm-lisboa.pt', '0', '887', '195202910', 'Mário Adriano C. Pinto Tavares', '9821852', '744520', '917078966', '', 'BilheteIdentidade', '1', '5', 'S5', '1', 0),
  ('102', '4334', '', '17-03-1975', 'nuno.pinheiro@cm-lisboa.pt', '0', '900', '206260652', 'Nuno Jorge Cruchinho Pinheiro', '10900290', '764009', '962393312', '', 'BilheteIdentidade', '2', '5', 'S4', '4', 0),
  ('103', '4207', '', '28-11-1966', 'joaquim.pica@cm-lisboa.pt', '0', '946', '184283558', 'Joaquim José Fialho Pica', '10070135', '406630', '967031299', '', 'BilheteIdentidade', '2', '5', 'S4', '2', 0),
  ('104', '4376', '', '09-07-1970', 'joao.bexiga@cm-lisboa.pt', '0', '968', '189251816', 'João Carlos Fatela Bexiga', '9654074', '357612', '964344914', '', 'BilheteIdentidade', '1', '5', 'S4', '2', 0),
  ('105', '4429', '', '24-11-1975', 'nuno.manuel.santos@cm-lisboa.pt', '0', '1001', '214453391', 'Nuno Manuel G. P. dos Santos', '11621933', '950091', '936291319', '', 'BilheteIdentidade', '1', '5', 'S4', '4', 0),
  ('106', '4495', '', '19-04-1978', 'luis.fortunato@cm-lisboa.pt', '0', '1027', '210488000', 'Luis Filipe O. de S.Fortunato', '11284524', '950044', '969292525', '', 'BilheteIdentidade', '2', '15', 'S4', '9', 0),
  ('107', '4503', '', '07-04-1977', 'david.carvalho@cm-lisboa.pt', '0', '1028', '214094790', 'David António B. Carvalho', '10972472', '950155', '965438003', '', 'BilheteIdentidade', '1', '5', 'S5', '3', 0),
  ('108', '4471', '', '10-05-1978', 'nuno.ramos@cm-lisboa.pt', '0', '1037', '216229871', 'Nuno Filipe dos Santos Ramos', '11242945', '0', '966167219', '', 'BilheteIdentidade', '2', '5', 'S4', '3', 0)
ON CONFLICT DO NOTHING
;
INSERT INTO elemento (id, matricula, abreviatura, data_ingresso, data_nascimento, e_mail, factor_elegibilidade, id_interno, nif, nome, num_doc_identificacao, num_mecanografico, telefone1, telefone2, tipo_doc_identificacao, instalacao_id, posto_funcional_id, tipo_presenca_id, turno_id, version) VALUES
  ('109', '3961', '', '11-12-1983', '01-03-1960', 'joao.curto@cm-lisboa.pt', '0', '0', '142670995', 'João Manuel Curto', '4455167', '374102', '913463261', '', 'BilheteIdentidade', '1', '15', '0', '9', 0),
  ('110', '4135', '', '01-04-1987', '13-04-1964', 'jose.m.oliveira@cm-lisboa.pt', '0', '0', '122263952', 'José Manuel Moleira de Oliveira', '7759924', '497757', '916410788', '', 'BilheteIdentidade', '1', '12', 'S1', '4', 0)
ON CONFLICT DO NOTHING
;
INSERT INTO elemento (id, matricula, abreviatura, data_nascimento, e_mail, factor_elegibilidade, id_interno, nif, nome, num_doc_identificacao, num_mecanografico, telefone1, telefone2, tipo_doc_identificacao, instalacao_id, posto_funcional_id, tipo_presenca_id, turno_id, version) VALUES
  ('111', '', '', '01-01-1970', '', '0', '0', '', '', '', '1', '', '', 'BilheteIdentidade', '1', '6', 'S', '2', 0),
  ('112', '', '', '01-01-1970', '', '0', '0', '', '', '', '2', '', '', 'BilheteIdentidade', '1', '12', 'S1', '9', 0),
  ('113', '', '', '01-01-1970', '', '0', '107', '', '', '', '3', '', '', 'BilheteIdentidade', '1', '9', 'S3', '4', 0),
  ('114', '', '', '01-01-1970', '', '0', '171', '', '', '', '4', '', '', 'BilheteIdentidade', '1', '9', 'S3', '1', 0),
  ('115', '', '', '01-01-1970', '', '0', '0', '', '', '', '5', '', '', 'BilheteIdentidade', '1', '9', 'S5', '2', 0),
  ('116', '', '', '01-01-1970', '', '0', '279', '', '', '', '6', '', '', 'BilheteIdentidade', '2', '9', 'S5', '4', 0),
  ('117', '', '', '01-01-1970', '', '0', '0', '', '', '', '7', '', '', 'BilheteIdentidade', '2', '11', 'S4', '2', 0),
  ('118', '', '', '01-01-1970', '', '0', '0', '', '', '', '8', '', '', 'BilheteIdentidade', '1', '5', 'J', '9', 0),
  ('119', '', '', '01-01-1970', '', '0', '540', '', '', '', '9', '', '', 'BilheteIdentidade', '1', '5', 'S5', '3', 0),
  ('120', '', '', '01-01-1970', '', '0', '0', '', '', '', '10', '', '', 'BilheteIdentidade', '1', '5', 'S', '4', 0),
  ('121', '', '', '01-01-1970', '', '0', '638', '', '', '', '11', '', '', 'BilheteIdentidade', '1', '5', 'S5', '4', 0),
  ('122', '', '', '01-01-1970', '', '0', '644', '', '', '', '12', '', '', 'BilheteIdentidade', '1', '5', 'S5', '3', 0),
  ('123', '', '', '01-01-1970', '', '0', '0', '', '', '', '13', '', '', 'BilheteIdentidade', '1', '5', 'S4', '4', 0),
  ('124', '', '', '01-01-1970', '', '0', '0', '', '', '', '14', '', '', 'BilheteIdentidade', '1', '15', '0', '9', 0),
  ('125', '', '', '01-01-1970', '', '0', '909', '', '', '', '15', '', '', 'BilheteIdentidade', '1', '5', 'S4', '4', 0),
  ('126', '', '', '01-01-1970', '', '0', '992', '', '', '', '16', '', '', 'BilheteIdentidade', '1', '5', 'S5', '4', 0),
  ('127', '', '', '01-01-1970', '', '0', '1013', '', '', '', '17', '', '', 'BilheteIdentidade', '1', '5', 'S5', '1', 0),
  ('128', '', '', '01-01-1970', '', '0', '674', '', '', '', '18', '', '', 'BilheteIdentidade', '1', '15', 'S5', '9', 0),
  ('129', '', '', '01-01-1970', '', '0', '725', '', '', '', '19', '', '', 'BilheteIdentidade', '1', '15', 'S5', '9', 0),
  ('130', '', '', '01-01-1970', '', '0', '688', '', '', '', '20', '', '', 'BilheteIdentidade', '1', '15', 'S5', '9', 0),
  ('131', '', '', '01-01-1970', '', '0', '1045', '', '', '', '21', '', '', 'BilheteIdentidade', '1', '15', 'S5', '9', 0),
  ('132', '', '', '01-01-1970', '', '0', '370', '', '', '', '22', '', '', 'BilheteIdentidade', '2', '5', 'S4', '4', 0),
  ('133', '', '', '01-01-1970', '', '0', '669', '', '', '', '23', '', '', 'BilheteIdentidade', '1', '5', 'S5', '2', 0),
  ('134', '', '', '01-01-1970', '', '0', '307', '', '', '', '24', '', '', 'BilheteIdentidade', '1', '5', 'S5', '9', 0),
  ('135', '', '', '01-01-1970', '', '0', '641', '', '', '', '25', '', '', 'BilheteIdentidade', '2', '5', 'S4', '3', 0),
  ('136', '', '', '01-01-1970', '', '0', '0', '', '', '', '26', '', '', 'BilheteIdentidade', '1', '6', 'S', '4', 0),
  ('137', '', '', '01-01-1970', '', '0', '0', '', '', '', '27', '', '', 'BilheteIdentidade', '1', '12', 'S1', '2', 0),
  ('138', '', '', '01-01-1970', '', '0', '217', '', '', '', '28', '', '', 'BilheteIdentidade', '1', '9', 'S3', '1', 0),
  ('139', '', '', '01-01-1970', '', '0', '295', '', '', '', '29', '', '', 'BilheteIdentidade', '2', '9', 'S3', '1', 0),
  ('140', '', '', '01-01-1970', '', '0', '736', '', '', '', '30', '', '', 'BilheteIdentidade', '1', '5', 'S5', '4', 0)
ON CONFLICT DO NOTHING
;

-- PERIODOS:
INSERT INTO periodo (id, dt_fim, dt_inicio, version) VALUES
  ('1', '31-12-2015', '01-12-2015', 0),
  ('2', '31-01-2016', '01-01-2016', 0)
ON CONFLICT DO NOTHING
;

-- CATEGORIAS
INSERT INTO categoria (id, abreviatura, descricao, nivel_hierarquico, quadro, version) VALUES
  ('1', 'Cmdt', 'Comandante', '0', 'COMANDO', 0),
  ('2', '2oCmdt', '2.º Comandante', '0', 'COMANDO', 0),
  ('3', 'Cmdt.Comp', 'Comandante de Companhia', '0', 'COMANDO', 0),
  ('4', 'Adj.Tec', 'Adjunto Técnico', '0', 'COMANDO', 0),
  ('5', 'Ch.Pr', 'Chefe Principal', '1', 'BOMBEIRO', 0),
  ('6', 'Ch.1a', 'Chefe de 1.ª Classe', '2', 'BOMBEIRO', 0),
  ('7', 'Ch.2a', 'Chefe de 2.ª Classe', '3', 'BOMBEIRO', 0),
  ('8', 'S/Ch.Pr', 'Subchefe Principal', '4', 'BOMBEIRO', 0),
  ('9', 'S/Ch.1a', 'Subchefe de 1.ª Classe', '5', 'BOMBEIRO', 0),
  ('10', 'S/Ch.2a', 'Subchefe de 2.ª Classe', '6', 'BOMBEIRO', 0),
  ('11', 'B.Sap', 'Bombeiro Sapador', '7', 'BOMBEIRO', 0),
  ('12', 'B.Rec', 'Bombeiro Sapador Recruta', '8', 'BOMBEIRO', 0),
  ('13', 'N/D', 'Não Disponível', '-1', 'OUTRO', 0)
ON CONFLICT DO NOTHING
;

-- RESPONSABILIDADES OPERACIONAIS
INSERT INTO responsabilidade_operacional (id, depende_factor_elegibilidade, designacao, sigla, tipo_servico, tipo_presenca_id, version) VALUES
  ('1', TRUE, 'Comandante de Permanencia às Operações', 'CPO', 'EXTERNO', 'S1', 0),
  ('2', TRUE, 'Chefe de Serviço à Cidade', 'CH CIDADE', 'EXTERNO', 'S', 0),
  ('3', TRUE, 'Chefe de 1ª Intervenção', 'CH 1ª Int.', 'EXTERNO', 'S2', 0),
  ('4', TRUE, 'Sub Chefe de Dia', 'SUBCH DIA', 'EXTERNO', 'S3', 0),
  ('5', TRUE, 'Sub Chefe 2ª de dia à Companhia', 'SUBCH 2ª DIA COMP.', 'INTERNO', 'S3', 0),
  ('6', TRUE, 'Sub Chefe 2ª ao Aparelho', 'SUBCH 2ª AP.', 'INTERNO', 'S3', 0),
  ('7', TRUE, 'Encarregado da Garagem', 'ENC. GARAGEM', 'INTERNO', 'S5', 0),
  ('8', TRUE, 'Pessoal de Linha', 'LINHA', 'EXTERNO', 'S4', 0),
  ('9', TRUE, 'Motorista', 'MOTORISTA', 'EXTERNO', 'S5', 0),
  ('10', TRUE, 'Subchefe 1ª', 'SUBCHEFE 1ª', 'EXTERNO', 'S3', 0),
  ('11', TRUE, 'Subchefe 2ª', 'SUBCHEFE 2ª', 'EXTERNO', 'S3', 0),
  ('12', TRUE, 'Linha', 'BOMBEIRO SAPADOR', 'EXTERNO', 'S4', 0),
  ('13', FALSE, 'Cozinha', 'Cozinha', 'INTERNO', 'S4', 0)
ON CONFLICT DO NOTHING
;

-- UNIDADES OPERACIONAIS
INSERT INTO unidade_operacional (id, designacao, operacional, instalacao_id, tipo_unidade_operacional_id, version) VALUES
  ('1', 'VLCI31', TRUE, '1', '1', 0),
  ('2', 'VUCI31', TRUE, '1', '2', 0),
  ('3', 'VE37', FALSE, '1', '3', 0),
  ('4', 'VTTU', TRUE, '1', '4', 0),
  ('5', 'VOPE', TRUE, '1', '5', 0),
  ('6', 'VCOT62', TRUE, '1', '6', 0),
  ('7', 'OPDAE', TRUE, '1', '7', 0),
  ('8', 'COZINHA', TRUE, '1', '8', 0),
  ('9', 'BAR', TRUE, '1', '9', 0),
  ('10', 'PLANTÃO GARAGEM', TRUE, '1', '10', 0)
ON CONFLICT DO NOTHING
;

-- GUARNIÇÃO
INSERT INTO guarnicao (id, maximo, minimo, responsabilidade_operacional_id, unidade_operacional_id, version) VALUES
  ('1', '1', '1', '3', '1', 0),
  ('2', '1', '1', '9', '1', 0),
  ('3', '3', '2', '12', '1', 0),
  ('4', '1', '1', '13', '8', 0)
ON CONFLICT DO NOTHING
;

-- ASSOCIAÇÃO RESPONSABILIDADES OPERACIONAIS / FORMAÇÃO
---- Adicionar constraint à associação de Responsabilidades Operacionais e Formações:
ALTER TABLE responsabilidade_formacao DROP CONSTRAINT IF EXISTS responsabilidade_operacional_id_formacao_id;
ALTER TABLE responsabilidade_formacao ADD CONSTRAINT responsabilidade_operacional_id_formacao_id UNIQUE (responsabilidade_operacional_id, formacao_id);
---- Adicionar os dados de associação. Falha se já existirem graças ao constraint definido acima.
INSERT INTO responsabilidade_formacao (responsabilidade_operacional_id, formacao_id) VALUES
  ('1', '5'),
  ('2', '5'),
  ('3', '5'),
  ('4', '5'),
  ('5', '5'),
  ('6', '5'),
  ('7', '1'),
  ('7', '2'),
  ('7', '3'),
  ('8', '5'),
  ('9', '5'),
  ('10', '5'),
  ('11', '5'),
  ('12', '5'),
  ('13', '4')
ON CONFLICT DO NOTHING
;

-- ASSOCIAÇÃO PESSOAL A CATEGORIAS, RESPECTIVAS CLASSIFICAÇÕES NA FORMAÇÃO ELEGÍVEL E DATA DE ATRIBUIÇÃO DA CATEGORIA
INSERT INTO atribuicao_categoria (id, classificacao_formacao, data_atribuicao_categoria, categoria_id, elemento_id, version) VALUES
  ('1', '0', '01-01-1970', '9', '1', 0),
  ('2', '0', '01-01-1970', '9', '2', 0),
  ('3', '0', '01-01-1970', '9', '3', 0),
  ('4', '0', '01-01-1970', '9', '4', 0),
  ('5', '0', '01-01-1970', '9', '5', 0),
  ('6', '0', '01-01-1970', '9', '6', 0),
  ('7', '0', '01-01-1970', '9', '7', 0),
  ('8', '0', '01-01-1970', '10', '8', 0),
  ('9', '0', '01-01-1970', '10', '9', 0),
  ('10', '0', '01-01-1970', '10', '10', 0),
  ('11', '0', '01-01-1970', '10', '11', 0),
  ('12', '0', '01-01-1970', '10', '12', 0),
  ('13', '0', '01-01-1970', '10', '13', 0),
  ('14', '0', '01-01-1970', '10', '14', 0),
  ('15', '0', '01-01-1970', '10', '15', 0),
  ('16', '0', '01-01-1970', '10', '16', 0),
  ('17', '0', '01-01-1970', '10', '17', 0),
  ('18', '0', '01-01-1970', '10', '18', 0),
  ('19', '0', '01-01-1970', '10', '19', 0),
  ('20', '0', '01-01-1970', '10', '20', 0),
  ('21', '0', '01-01-1970', '10', '21', 0),
  ('23', '0', '01-01-1970', '10', '23', 0),
  ('24', '0', '01-01-1970', '10', '24', 0),
  ('25', '0', '01-01-1970', '10', '25', 0),
  ('26', '0', '01-01-1970', '10', '26', 0),
  ('27', '0', '01-01-1970', '10', '27', 0),
  ('28', '0', '01-01-1970', '10', '28', 0),
  ('29', '0', '01-01-1970', '10', '29', 0),
  ('30', '0', '01-01-1970', '10', '30', 0),
  ('31', '0', '01-01-1970', '10', '31', 0),
  ('32', '0', '01-01-1970', '10', '32', 0),
  ('33', '0', '01-01-1970', '10', '33', 0),
  ('34', '0', '01-01-1970', '10', '34', 0),
  ('35', '0', '01-01-1970', '11', '35', 0),
  ('36', '0', '01-01-1970', '11', '36', 0),
  ('37', '0', '01-01-1970', '11', '37', 0),
  ('38', '0', '01-01-1970', '11', '38', 0),
  ('39', '0', '01-01-1970', '11', '39', 0),
  ('40', '0', '01-01-1970', '11', '40', 0),
  ('41', '0', '01-01-1970', '11', '41', 0),
  ('42', '0', '01-01-1970', '11', '42', 0),
  ('43', '0', '01-01-1970', '11', '43', 0),
  ('44', '0', '01-01-1970', '11', '44', 0),
  ('45', '0', '01-01-1970', '11', '45', 0),
  ('46', '0', '01-01-1970', '11', '46', 0),
  ('47', '0', '01-01-1970', '11', '47', 0),
  ('48', '0', '01-01-1970', '11', '48', 0),
  ('49', '0', '01-01-1970', '11', '49', 0),
  ('50', '0', '01-01-1970', '11', '50', 0),
  ('51', '0', '01-01-1970', '11', '51', 0),
  ('52', '0', '01-01-1970', '11', '52', 0),
  ('53', '0', '01-01-1970', '11', '53', 0),
  ('54', '0', '01-01-1970', '11', '54', 0),
  ('55', '0', '01-01-1970', '11', '55', 0),
  ('56', '0', '01-01-1970', '11', '56', 0),
  ('57', '0', '01-01-1970', '11', '57', 0),
  ('58', '0', '01-01-1970', '11', '58', 0),
  ('59', '0', '01-01-1970', '11', '59', 0),
  ('60', '0', '01-01-1970', '11', '60', 0),
  ('61', '0', '01-01-1970', '11', '61', 0),
  ('62', '0', '01-01-1970', '11', '62', 0),
  ('63', '0', '01-01-1970', '11', '63', 0),
  ('64', '0', '01-01-1970', '11', '64', 0),
  ('65', '0', '01-01-1970', '11', '65', 0),
  ('66', '0', '01-01-1970', '11', '66', 0),
  ('67', '0', '01-01-1970', '11', '67', 0),
  ('68', '0', '01-01-1970', '11', '68', 0),
  ('69', '0', '01-01-1970', '11', '69', 0),
  ('70', '0', '01-01-1970', '11', '70', 0),
  ('71', '0', '01-01-1970', '11', '71', 0),
  ('72', '0', '01-01-1970', '11', '72', 0),
  ('73', '0', '01-01-1970', '11', '73', 0),
  ('74', '0', '01-01-1970', '11', '74', 0),
  ('75', '0', '01-01-1970', '11', '75', 0),
  ('76', '0', '01-01-1970', '11', '76', 0),
  ('77', '0', '01-01-1970', '11', '77', 0),
  ('78', '0', '01-01-1970', '11', '78', 0),
  ('79', '0', '01-01-1970', '11', '79', 0),
  ('80', '0', '01-01-1970', '11', '80', 0),
  ('81', '0', '01-01-1970', '11', '81', 0),
  ('82', '0', '01-01-1970', '11', '82', 0),
  ('83', '0', '01-01-1970', '11', '83', 0),
  ('84', '0', '01-01-1970', '11', '84', 0),
  ('85', '0', '01-01-1970', '11', '85', 0),
  ('86', '0', '01-01-1970', '11', '86', 0),
  ('87', '0', '01-01-1970', '11', '87', 0),
  ('88', '0', '01-01-1970', '11', '88', 0),
  ('89', '0', '01-01-1970', '11', '89', 0),
  ('90', '0', '01-01-1970', '11', '90', 0),
  ('91', '0', '01-01-1970', '11', '91', 0),
  ('92', '0', '01-01-1970', '11', '92', 0),
  ('93', '0', '01-01-1970', '11', '93', 0),
  ('94', '0', '01-01-1970', '11', '94', 0),
  ('95', '0', '01-01-1970', '11', '95', 0),
  ('96', '0', '01-01-1970', '11', '96', 0),
  ('97', '0', '01-01-1970', '11', '97', 0),
  ('98', '0', '01-01-1970', '11', '98', 0),
  ('99', '0', '01-01-1970', '11', '99', 0),
  ('100', '0', '01-01-1970', '11', '100', 0),
  ('101', '0', '01-01-1970', '11', '101', 0),
  ('102', '0', '01-01-1970', '11', '102', 0),
  ('103', '0', '01-01-1970', '11', '103', 0),
  ('104', '0', '01-01-1970', '11', '104', 0),
  ('105', '0', '01-01-1970', '11', '105', 0),
  ('106', '0', '01-01-1970', '11', '106', 0),
  ('107', '0', '01-01-1970', '11', '107', 0),
  ('108', '0', '01-01-1970', '11', '108', 0),
  ('109', '0', '01-01-1970', '7', '109', 0),
  ('110', '0', '01-01-1970', '8', '110', 0),
  ('111', '0', '01-01-1970', '7', '111', 0),
  ('112', '0', '01-01-1970', '13', '112', 0),
  ('113', '0', '01-01-1970', '13', '113', 0),
  ('114', '0', '01-01-1970', '13', '114', 0),
  ('115', '0', '01-01-1970', '13', '115', 0),
  ('116', '0', '01-01-1970', '13', '116', 0),
  ('117', '0', '01-01-1970', '13', '117', 0),
  ('118', '0', '01-01-1970', '13', '118', 0),
  ('119', '0', '01-01-1970', '13', '119', 0),
  ('120', '0', '01-01-1970', '13', '120', 0),
  ('121', '0', '01-01-1970', '13', '121', 0),
  ('122', '0', '01-01-1970', '13', '122', 0),
  ('123', '0', '01-01-1970', '13', '123', 0),
  ('124', '0', '01-01-1970', '13', '124', 0),
  ('125', '0', '01-01-1970', '13', '125', 0),
  ('126', '0', '01-01-1970', '13', '126', 0),
  ('127', '0', '01-01-1970', '13', '127', 0),
  ('128', '0', '01-01-1970', '13', '128', 0),
  ('129', '0', '01-01-1970', '13', '129', 0),
  ('130', '0', '01-01-1970', '13', '130', 0),
  ('131', '0', '01-01-1970', '13', '131', 0),
  ('132', '0', '01-01-1970', '13', '132', 0),
  ('133', '0', '01-01-1970', '13', '133', 0),
  ('134', '0', '01-01-1970', '13', '134', 0),
  ('135', '0', '01-01-1970', '13', '135', 0),
  ('136', '0', '01-01-1970', '13', '136', 0),
  ('137', '0', '01-01-1970', '13', '137', 0),
  ('138', '0', '01-01-1970', '13', '138', 0),
  ('139', '0', '01-01-1970', '13', '139', 0),
  ('140', '0', '01-01-1970', '13', '140', 0)
ON CONFLICT DO NOTHING
;

-- FORMAÇÃO DO PESSOAL
INSERT INTO registo_formacao(id, data_aquisicao_formacao, data_caducidade_formacao, formacao_id, elemento_id, version) VALUES
  ('1', '01-01-1970', '01-01-2020', '1', '1', 0),
  ('2', '01-01-1970', '01-01-2020', '2', '1', 0),
  ('3', '01-01-1970', '01-01-2020', '3', '1', 0),
  ('4', '01-01-1970', '01-01-2020', '4', '1', 0),
  ('5', '01-01-1970', '01-01-2020', '5', '5', 0),
  ('6', '01-01-1970', '01-01-2020', '5', '6', 0),
  ('7', '01-01-1970', '01-01-2020', '5', '7', 0),
  ('8', '01-01-1970', '01-01-2020', '5', '8', 0),
  ('9', '01-01-1970', '01-01-2020', '5', '9', 0),
  ('10', '01-01-1970', '01-01-2020', '1', '10', 0),
  ('11', '01-01-1970', '01-01-2020', '2', '10', 0),
  ('12', '01-01-1970', '01-01-2020', '3', '10', 0),
  ('13', '01-01-1970', '01-01-2020', '4', '10', 0),
  ('14', '01-01-1970', '01-01-2020', '1', '11', 0),
  ('15', '01-01-1970', '01-01-2020', '4', '12', 0),
  ('16', '01-01-1970', '01-01-2020', '3', '13', 0),
  ('17', '01-01-1970', '01-01-2020', '5', '13', 0)
ON CONFLICT DO NOTHING
;

-- PRESENÇAS
INSERT INTO presenca (id, data, hora_inicio, num_horas, instalacao_efectiva_id, periodo_id, elemento_id, posto_funcional_efectivo_id, tipo_presenca_efectiva_id, turno_efectivo_id, version) VALUES
  ('1', '01-12-2015', '20:00', '12', '1', '1', '111', '6', 'X', '2', 0),
  ('2', '01-12-2015', '08:00', '0', '1', '1', '112', '12', 'T', '9', 0),
  ('3', '02-12-2015', '08:00', '12', '1', '1', '110', '12', 'A', '4', 0),
  ('4', '01-12-2015', '20:00', '12', '1', '1', '1', '13', 'S2', '2', 0),
  ('5', '03-12-2015', '08:00', '12', '1', '1', '2', '13', 'S2', '1', 0),
  ('6', '01-12-2015', '08:00', '0', '2', '1', '3', '7', '0', '9', 0),
  ('7', '02-12-2015', '08:00', '12', '1', '1', '4', '13', 'S2', '4', 0),
  ('8', '01-12-2015', '08:00', '12', '1', '1', '5', '13', 'X', '3', 0),
  ('9', '03-12-2015', '08:00', '12', '2', '1', '6', '8', 'S3', '1', 0),
  ('10', '01-12-2015', '20:00', '12', '1', '1', '7', '13', 'S2', '2', 0),
  ('11', '02-12-2015', '08:00', '12', '1', '1', '113', '9', 'S3', '4', 0),
  ('12', '01-12-2015', '08:00', '12', '1', '1', '8', '9', 'S5', '3', 0),
  ('13', '01-12-2015', '20:00', '12', '1', '1', '9', '9', 'S3', '2', 0),
  ('14', '01-12-2015', '20:00', '12', '1', '1', '10', '11', 'S3', '2', 0),
  ('15', '01-12-2015', '08:00', '12', '1', '1', '11', '9', 'S3', '3', 0),
  ('16', '03-12-2015', '08:00', '12', '1', '1', '114', '9', 'S3', '1', 0),
  ('17', '01-12-2015', '20:00', '12', '1', '1', '115', '9', 'X', '2', 0),
  ('18', '02-12-2015', '08:00', '12', '1', '1', '13', '9', 'S5', '4', 0),
  ('19', '02-12-2015', '08:00', '12', '1', '1', '14', '9', 'S3', '4', 0),
  ('20', '03-12-2015', '08:00', '12', '2', '1', '15', '11', 'S3', '1', 0),
  ('21', '01-12-2015', '20:00', '12', '2', '1', '19', '9', 'S3', '2', 0),
  ('22', '02-12-2015', '08:00', '12', '2', '1', '20', '9', 'S3', '4', 0),
  ('23', '01-12-2015', '08:00', '12', '1', '1', '21', '9', 'S3', '3', 0),
  ('25', '03-12-2015', '08:00', '12', '2', '1', '23', '11', 'S5', '1', 0),
  ('26', '01-12-2015', '08:00', '12', '2', '1', '24', '11', 'S3', '3', 0),
  ('27', '01-12-2015', '20:00', '12', '1', '1', '25', '9', 'S3', '2', 0),
  ('28', '02-12-2015', '08:00', '12', '2', '1', '116', '9', 'S5', '4', 0),
  ('29', '01-12-2015', '20:00', '12', '1', '1', '26', '9', 'S3', '2', 0),
  ('30', '02-12-2015', '08:00', '12', '2', '1', '27', '9', 'S3', '4', 0),
  ('31', '01-12-2015', '08:00', '0', '2', '1', '28', '10', '0', '9', 0),
  ('32', '01-12-2015', '20:00', '12', '2', '1', '117', '11', 'S4', '2', 0),
  ('33', '03-12-2015', '08:00', '12', '1', '1', '30', '9', 'X', '1', 0),
  ('34', '01-12-2015', '08:00', '12', '1', '1', '31', '11', 'S4', '3', 0),
  ('35', '03-12-2015', '08:00', '12', '1', '1', '32', '11', 'X', '1', 0),
  ('36', '01-12-2015', '08:00', '12', '1', '1', '33', '11', 'S5', '3', 0),
  ('37', '01-12-2015', '08:00', '12', '1', '1', '34', '9', 'B', '3', 0),
  ('38', '03-12-2015', '08:00', '12', '1', '1', '35', '5', 'L', '1', 0),
  ('39', '01-12-2015', '08:00', '12', '1', '1', '36', '5', 'S0', '3', 0),
  ('40', '02-12-2015', '08:00', '12', '1', '1', '37', '5', 'S4', '4', 0),
  ('41', '02-12-2015', '08:00', '12', '1', '1', '38', '5', 'S4', '4', 0),
  ('42', '01-12-2015', '20:00', '12', '1', '1', '39', '5', 'X', '2', 0),
  ('43', '01-12-2015', '08:00', '12', '1', '1', '40', '5', 'S4', '3', 0),
  ('44', '03-12-2015', '08:00', '12', '1', '1', '41', '5', 'S4', '1', 0),
  ('45', '01-12-2015', '08:00', '12', '1', '1', '42', '5', 'S5', '3', 0),
  ('46', '01-12-2015', '08:00', '12', '1', '1', '43', '5', 'S4', '3', 0),
  ('47', '02-12-2015', '08:00', '12', '1', '1', '44', '5', 'S5', '4', 0),
  ('48', '01-12-2015', '08:00', '12', '1', '1', '45', '5', 'S4', '3', 0),
  ('49', '03-12-2015', '08:00', '12', '1', '1', '46', '5', 'X', '1', 0),
  ('50', '01-12-2015', '20:00', '12', '1', '1', '47', '5', 'S4', '2', 0),
  ('51', '02-12-2015', '08:00', '12', '1', '1', '48', '5', 'S5', '4', 0),
  ('52', '01-12-2015', '08:00', '12', '1', '1', '50', '5', 'S4', '3', 0),
  ('53', '01-12-2015', '20:00', '12', '1', '1', '51', '5', 'B', '2', 0),
  ('54', '01-12-2015', '20:00', '12', '1', '1', '52', '5', 'S4', '2', 0),
  ('55', '01-12-2015', '08:00', '0', '1', '1', '118', '5', '0', '9', 0),
  ('56', '01-12-2015', '08:00', '12', '2', '1', '54', '5', 'S4', '3', 0),
  ('57', '03-12-2015', '08:00', '12', '1', '1', '55', '5', 'S4', '1', 0),
  ('58', '02-12-2015', '08:00', '12', '1', '1', '56', '5', 'S4', '4', 0),
  ('59', '01-12-2015', '08:00', '12', '1', '1', '57', '5', 'S4', '3', 0),
  ('60', '01-12-2015', '20:00', '12', '1', '1', '58', '5', 'S4', '2', 0),
  ('61', '01-12-2015', '08:00', '12', '1', '1', '119', '5', 'B', '3', 0),
  ('62', '03-12-2015', '08:00', '12', '1', '1', '59', '5', 'E', '1', 0),
  ('63', '02-12-2015', '08:00', '12', '1', '1', '60', '5', 'S4', '4', 0),
  ('64', '01-12-2015', '08:00', '12', '2', '1', '61', '5', 'S5', '3', 0),
  ('65', '02-12-2015', '08:00', '12', '2', '1', '62', '5', 'S4', '4', 0),
  ('66', '01-12-2015', '20:00', '12', '2', '1', '63', '5', 'S4', '2', 0),
  ('67', '02-12-2015', '08:00', '12', '1', '1', '64', '5', 'S4', '4', 0),
  ('68', '01-12-2015', '20:00', '12', '1', '1', '65', '5', 'X', '2', 0),
  ('69', '01-12-2015', '20:00', '12', '1', '1', '66', '5', 'S5', '2', 0),
  ('70', '01-12-2015', '20:00', '12', '1', '1', '67', '5', 'S4', '2', 0),
  ('71', '03-12-2015', '08:00', '12', '1', '1', '68', '5', 'S4', '1', 0),
  ('72', '03-12-2015', '08:00', '12', '1', '1', '69', '5', 'X', '1', 0),
  ('73', '02-12-2015', '08:00', '12', '1', '1', '120', '5', 'S', '4', 0),
  ('74', '03-12-2015', '08:00', '12', '2', '1', '71', '5', 'S4', '1', 0),
  ('75', '02-12-2015', '08:00', '12', '2', '1', '72', '5', 'S5', '4', 0),
  ('76', '01-12-2015', '20:00', '12', '2', '1', '73', '5', 'S4', '2', 0),
  ('77', '01-12-2015', '08:00', '12', '1', '1', '74', '5', 'S4', '3', 0),
  ('78', '02-12-2015', '08:00', '12', '1', '1', '121', '5', 'S0', '4', 0),
  ('79', '01-12-2015', '08:00', '12', '1', '1', '122', '5', 'S5', '3', 0),
  ('80', '01-12-2015', '20:00', '12', '2', '1', '75', '5', 'S4', '2', 0),
  ('81', '03-12-2015', '08:00', '12', '1', '1', '77', '5', 'S5', '1', 0),
  ('82', '02-12-2015', '08:00', '12', '1', '1', '78', '5', 'S4', '4', 0),
  ('83', '03-12-2015', '08:00', '12', '2', '1', '79', '5', 'S4', '1', 0),
  ('84', '01-12-2015', '08:00', '12', '1', '1', '80', '5', 'S4', '3', 0),
  ('85', '02-12-2015', '08:00', '12', '2', '1', '82', '5', 'S4', '4', 0),
  ('86', '02-12-2015', '08:00', '12', '1', '1', '123', '5', 'S4', '4', 0),
  ('87', '02-12-2015', '08:00', '12', '2', '1', '84', '5', 'S4', '4', 0),
  ('88', '01-12-2015', '20:00', '12', '1', '1', '85', '5', 'S5', '2', 0),
  ('89', '03-12-2015', '08:00', '12', '2', '1', '86', '5', 'S4', '1', 0),
  ('90', '01-12-2015', '08:00', '12', '2', '1', '87', '5', 'S4', '3', 0),
  ('91', '01-12-2015', '08:00', '12', '1', '1', '88', '5', 'S4', '3', 0),
  ('92', '01-12-2015', '20:00', '12', '1', '1', '91', '5', 'S5', '2', 0),
  ('93', '01-12-2015', '08:00', '12', '2', '1', '92', '5', 'S4', '3', 0),
  ('94', '01-12-2015', '20:00', '12', '2', '1', '93', '5', 'I', '2', 0),
  ('95', '03-12-2015', '08:00', '12', '2', '1', '94', '5', 'S4', '1', 0),
  ('96', '03-12-2015', '08:00', '12', '2', '1', '95', '5', 'S4', '1', 0),
  ('97', '01-12-2015', '08:00', '12', '1', '1', '96', '5', 'S0', '3', 0),
  ('98', '03-12-2015', '08:00', '12', '1', '1', '97', '5', 'S4', '1', 0),
  ('99', '03-12-2015', '08:00', '12', '1', '1', '98', '5', 'S4', '1', 0),
  ('100', '03-12-2015', '08:00', '12', '1', '1', '99', '5', 'S4', '1', 0),
  ('101', '01-12-2015', '20:00', '12', '1', '1', '100', '5', 'B', '2', 0),
  ('102', '03-12-2015', '08:00', '12', '1', '1', '101', '5', 'S5', '1', 0),
  ('103', '02-12-2015', '08:00', '12', '2', '1', '102', '5', 'S4', '4', 0),
  ('104', '02-12-2015', '08:00', '12', '1', '1', '125', '5', 'S4', '4', 0),
  ('105', '01-12-2015', '20:00', '12', '2', '1', '103', '5', 'X', '2', 0),
  ('106', '01-12-2015', '20:00', '12', '1', '1', '104', '5', 'S4', '2', 0),
  ('107', '02-12-2015', '08:00', '12', '1', '1', '126', '5', 'S0', '4', 0),
  ('108', '02-12-2015', '08:00', '12', '1', '1', '105', '5', 'S4', '4', 0),
  ('109', '03-12-2015', '08:00', '12', '1', '1', '127', '5', 'S5', '1', 0),
  ('110', '01-12-2015', '08:00', '12', '1', '1', '107', '5', 'S5', '3', 0),
  ('111', '01-12-2015', '08:00', '12', '2', '1', '108', '5', 'S4', '3', 0),
  ('112', '02-12-2015', '08:00', '12', '2', '1', '132', '5', 'S4', '4', 0),
  ('113', '01-12-2015', '20:00', '12', '1', '1', '133', '5', 'S5', '2', 0),
  ('114', '01-12-2015', '08:00', '0', '1', '1', '134', '5', 'X', '9', 0),
  ('115', '01-12-2015', '08:00', '12', '2', '1', '135', '5', 'S0', '3', 0),
  ('116', '02-12-2015', '08:00', '12', '1', '1', '136', '6', 'S', '4', 0),
  ('117', '01-12-2015', '20:00', '12', '1', '1', '137', '12', 'S2', '2', 0),
  ('118', '03-12-2015', '08:00', '12', '1', '1', '138', '9', 'S4', '1', 0),
  ('119', '03-12-2015', '08:00', '12', '2', '1', '139', '9', 'B', '1', 0),
  ('120', '02-12-2015', '08:00', '12', '1', '1', '140', '5', 'S4', '4', 0)
ON CONFLICT DO NOTHING
;
INSERT INTO presenca (id, data, hora_inicio, num_horas, elemento_reforco_id, instalacao_efectiva_id, periodo_id, elemento_id, posto_funcional_efectivo_id, tipo_presenca_efectiva_id, turno_efectivo_id, version) VALUES
  ('121', '01-12-2015', '20:00', '12', '13', '1', '1', '16', '9', 'S0', '2', 0)
ON CONFLICT DO NOTHING
;
INSERT INTO presenca (id, data, hora_inicio, num_horas, elemento_reforcado_id, instalacao_efectiva_id, periodo_id, elemento_id, posto_funcional_efectivo_id, tipo_presenca_efectiva_id, turno_efectivo_id, version) VALUES
  ('122', '01-12-2015', '20:00', '12', '16', '1', '1', '13', '9', '5S', '2', 0)
ON CONFLICT DO NOTHING
;

-- ACTUALIZA AS SEQUENCIAS DE ID:
SELECT SETVAL('public.algoritmo_calculo_turno_id_seq', COALESCE(MAX(id), 1) ) FROM public.algoritmo_calculo_turno;
SELECT SETVAL('public.categoria_id_seq', COALESCE(MAX(id), 1) ) FROM public.categoria;
SELECT SETVAL('public.formacao_id_seq', COALESCE(MAX(id), 1) ) FROM public.formacao;
SELECT SETVAL('public.guarnicao_id_seq', COALESCE(MAX(id), 1) ) FROM public.guarnicao;
SELECT SETVAL('public.instalacao_id_seq', COALESCE(MAX(id), 1) ) FROM public.instalacao;
SELECT SETVAL('public.periodo_id_seq', COALESCE(MAX(id), 1) ) FROM public.periodo;
SELECT SETVAL('public.elemento_id_seq', COALESCE(MAX(id), 1) ) FROM public.elemento;
SELECT SETVAL('public.atribuicao_categoria_id_seq', COALESCE(MAX(id), 1) ) FROM public.atribuicao_categoria;
SELECT SETVAL('public.posto_funcional_id_seq', COALESCE(MAX(id), 1) ) FROM public.posto_funcional;
SELECT SETVAL('public.presenca_id_seq', COALESCE(MAX(id), 1) ) FROM public.presenca;
SELECT SETVAL('public.registo_formacao_id_seq', COALESCE(MAX(id), 1) ) FROM public.registo_formacao;
SELECT SETVAL('public.responsabilidade_operacional_id_seq', COALESCE(MAX(id), 1) ) FROM public.responsabilidade_operacional;
SELECT SETVAL('public.tipo_unidade_estrutural_id_seq', COALESCE(MAX(id), 1) ) FROM public.tipo_unidade_estrutural;
SELECT SETVAL('public.tipo_unidade_operacional_id_seq', COALESCE(MAX(id), 1) ) FROM public.tipo_unidade_operacional;
SELECT SETVAL('public.turno_id_seq', COALESCE(MAX(id), 1) ) FROM public.turno;
SELECT SETVAL('public.unidade_estrutural_id_seq', COALESCE(MAX(id), 1) ) FROM public.unidade_estrutural;
SELECT SETVAL('public.unidade_operacional_id_seq', COALESCE(MAX(id), 1) ) FROM public.unidade_operacional;
