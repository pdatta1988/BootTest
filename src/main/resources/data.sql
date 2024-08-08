CREATE TABLE resource (
                          resource_id int,
                          resource_name varchar(255)

);

CREATE TABLE booking (
                          booking_id int,
                          booking_slot varchar(255),
                          booking_date DATE,
                          resource_id int


);



insert into resource(resource_id,resource_name) values ('1001','Portable 32 Slice CT Scanner');
insert into resource(resource_id,resource_name) values ('1002','Motion Mobile X-Ray System');
insert into resource(resource_id,resource_name) values ('1003','30 mA Portable X-Ray Machine');
insert into resource(resource_id,resource_name) values ('1004','DX-D 100 DR System');
insert into resource(resource_id,resource_name) values ('1005','100ma Mobile X-Ray Machine');

insert into booking(booking_id,booking_slot,booking_date,resource_id) values ('1001','11:00 - 12:00','2017-12-31','1001');
insert into booking(booking_id,booking_slot,booking_date,resource_id) values ('1002','11:00 - 12:00','2017-12-30','1002');

commit ;