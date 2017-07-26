drop table if exists `kategorija`;
create table `kategorija`(
	`id` int(5) not null auto_increment,
    `naziv_kategorije` varchar(40),
    primary key (`id`)
);

DROP TABLE IF EXISTS `kompanija`;
CREATE TABLE `kompanija` (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `naziv` varchar(60) not null,
  `kategorija_posla` int(5) not null,
  `mail` varchar(70) not null,
  `ponudjen_posao` boolean,
  `agent` varchar(20) not null,/*collation utf8-utf8_bin*/
  PRIMARY KEY (`id`),
  KEY `agentFK_idx` (`agent`),
  FOREIGN KEY (`kategorija_posla`) REFERENCES `kategorija` (`id`)
);

ALTER TABLE kompanija MODIFY agent VARCHAR(64) CHARACTER SET utf8 COLLATE utf8_bin;
ALTER TABLE kompanija ADD CONSTRAINT agentFK FOREIGN KEY (agent) REFERENCES act_id_user(ID_);

drop table if exists `ponuda_kompanije`;
create table `ponuda_kompanije`(
	`id` int(5) not null auto_increment,
    `kompanija_id` int(5) not null,
    `cena` int not null,
    /*`process_instance_id` varchar(67) not null, collation utf8-utf8_bin*/
    primary key(`id`),
    key `kompanijaFk_idx`(`kompanija_id`),
    foreign key (`kompanija_id`) references kompanija(id)
  );
    
insert into kategorija value(1, 'Popravka racunara'), (2, 'Nadogradnja racunara'),
 (3, 'Izrada web aplikacija'), (4, 'Backup podataka');
 
insert into act_id_user value('webworks', null, null, null,null, 'webworks', null),('laptopcentar', null, null, null,null, 'laptopcentar', null),
('sectorm', null, null, null,null, 'sectorm', null),('mgcomputers', null, null, null,null, 'mgcomputers', null),
('itfix', null, null, null,null, 'itfix', null),('incomputers', null, null, null,null, 'incomputers', null),
('comtrade', null, null, null,null, 'comtrade', null),('gigatron', null, null, null,null, 'gigatron', null),
('winwin', null, null, null,null, 'winwin', null),('bgdesign', null, null, null,null, 'bgdesign', null),
('qodeinteractive', null, null, null,null, 'qodeinteractive', null),('kelcix', null, null, null,null, 'kelcix', null),
('tmns', null, null, null,null, 'tmns', null),('tradecore', null, null, null,null, 'tradecore', null),
('exlrt', null, null, null,null, 'exlrt', null),('k3solutions', null, null, null,null, 'k3solutions', null),('meritsolutions', null, null, null,null, 'meritsolutions', null);

insert into kompanija value(1, 'Web works', 3, 'webworks@localhost', 0, 'webworks'), (2, 'Laptop centar', 1, 'laptopcentar@localhost',  0, 'laptopcentar'),
(3, 'Sector M', 1, 'sectorm@localhost',  0, 'sectorm'),(4, 'MG Comuters', 1, 'mgcomputers@localhost',  0, 'mgcomputers'), (5, 'IT Fix', 1, 'itfix@localhost',  0, 'itfix'),
(6, 'IN Computers', 1, 'incomputers@localhost',  0, 'incomputers'), (7, 'Comtrade', 2, 'comtrade@localhost',  0, 'comtrade'), (8, 'Gigatron', 2, 'gigatron@localhost',  0, 'gigatron'),
(9, 'WinWin', 2, 'winwin@localhost',  0, 'winwin'), (10, 'BG design', 3, 'bgdesign@localhost',  0, 'bgdesign'), (11, 'Qode Interactive', 4, 'qodeinteractive@localhost', 0, 'qodeinteractive'),
(12, 'Kelcix', 4, 'kelcix@localhost', 0, 'kelcix'),(13, 'TMNS', 3, 'tmns@localhost', 0, 'tmns'),(14, 'TradeCore', 4, 'tradecore@localhost', 0, 'tradecore'),
(15, 'EXLRT', 3, 'exlrt@localhost', 0, 'exlrt'),(16, 'K3 Solutions', 3, 'k3solutions@localhost', 0, 'k3solutions'),(17, 'Merit Solutions', 3, 'meritsolutions@localhost', 0, 'meritsolutions');
