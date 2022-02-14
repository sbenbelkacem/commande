CREATE TABLE IF NOT EXISTS commande (
                                       id integer primary key,
                                       product_id integer not null,
                                       status varchar(64) not null,
                                       quantity integer not null,
                                       total_price integer,
                                       order_date timestamp
);
insert into commande (id,product_id,status,quantity, total_price, order_date) values (1,1,'A_VALIDER',1,20,null);
insert into commande (id,product_id,status,quantity, total_price, order_date) values (2,2,'EN_COURS_DE_LIVRAISON',2,30,current_timestamp);
insert into commande (id,product_id,status,quantity, total_price, order_date) values (3,3,'LIVRE',1,30,null);
insert into commande (id,product_id,status,quantity, total_price, order_date) values (4,4,'COMFIRME',1,90,null);