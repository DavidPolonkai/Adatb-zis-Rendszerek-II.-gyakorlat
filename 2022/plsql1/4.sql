DECLARE
 l_seed char(100);
 r number(4);
BEGIN
 l_seed:=to_char(SYSTIMESTAMP,'YYYYDDMMHH24MISSFFFF');
 r := dbms_random.value(-100,100);
 r := :r;
 if r<0 then
    dbms_output.put_line(r||' negatív');
 elsif r=0 then
    dbms_output.put_line(r||' ez nulla');
 else
   dbms_output.put_line(r||' pozitív ');
 end if;
END;

--select dbms_random.value(1,100) from dual;
