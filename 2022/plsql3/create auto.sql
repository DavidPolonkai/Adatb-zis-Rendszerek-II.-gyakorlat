create table auto(
    rsz char(6) primary key,
    tipus char(10) not null,
    szin char(10) default 'feher',
    evjarat number(4),
    ar number(8) check(ar>0)
);

insert into auto values('AAA000','skoda','zöld',2008,200000);
insert into auto(rsz,tipus,evjarat,ar) values('AAA001','skoda',2010,1200000);
insert into auto values('AAA002','suzuki','piros',2000,20000);

commit;

select * from auto