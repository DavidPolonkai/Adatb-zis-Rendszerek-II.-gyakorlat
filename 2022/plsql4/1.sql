CREATE OR REPLACE PROCEDURE OLDAUTO(hatar IN number, rek_szam IN OUT number) is
    zero_record exception;
begin
    update auto set ar=ar*0.8 where to_char(sysdate,'yyyy')-evjarat>hatar;
    rek_szam := sql%rowcount;
    if sql%notfound then
        raise zero_record;
    elsif sql%found then
        dbms_output.put_line('yeeey');
    end if;
    exception
        when zero_record then dbms_output.put_line('Nincs');
end;

declare 
    akor number(2) :=13;
    rszam number(2);
begin
    oldauto(akor,rszam);
    dbms_output.put_line('Módosított adatok: '|| rszam);
end;

--autok árának alsó határa paraméterként, 
--autok árának felső határa paraméterként,
--autok új árát paraméter
--in out mennyit kellene módosítani

CREATE OR REPLACE PROCEDURE arazo 
(alsoar in number,felsoar in number, ujar in number, preferaltmodositasok in out number) is

begin
    update auto set ar=ujar where ar BETWEEN alsoar AND felsoar;
    if sql%rowcount=preferaltmodositasok then
        dbms_output.put_line('jó');
    else
        preferaltmodositasok := sql%rowcount;
    end if;
end;
declare
    pref number(2):=3;
begin
    arazo(10000,200000,100000,pref);
    dbms_output.put_line('valós változatatott: '|| pref);
end;

select * from auto
