create or replace procedure tomb(mini out integer, maxi out integer) is
    type ttipus is varray(10) of integer;
    elemek ttipus := ttipus(1,2,3,4,5);
    l_seed char(100);
begin
    l_seed := to_char(systimestamp,'YYYYDDMMHH24MISSFFFF');
    --for i in 0..8 loop
        --select dbms_random.value(-100,100) into elemek(1) from dual;
        dbms_output.put_line(1 || '. elem: ' || elemek(1));
    --end loop;
end;

set serveroutput on


create or replace procedure tomb2(n in number,mini out integer, maxi out integer) is
    type ttipus is table of integer;
    elemek ttipus := ttipus();
    l_seed char(100);
begin
    l_seed := to_char(systimestamp,'YYYYDDMMHH24MISSFFFF');
    for i in 1..n loop
        elemek.extend(1);
        select dbms_random.value(-100,100) into elemek(i) from dual;
        dbms_output.put_line(i || '. elem: ' || elemek(i));
    end loop;
    
    mini:=elemek(1);
    maxi:=elemek(1);
    
    for i in 1..n loop
        if mini>elemek(i) then
            mini := elemek(i);
        end if;
        if maxi<elemek(i) then
            maxi := elemek(i);
        end if;
    end loop;
    
end;

declare
    mini integer;
    maxi integer;
begin
    tomb2(10,mini,maxi);
    dbms_output.put_line('min: '||mini ||'max: '|| maxi);
end;



