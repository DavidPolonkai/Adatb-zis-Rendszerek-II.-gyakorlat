declare 
    cursor pauto (sz char) is select * from auto where szin=sz;
    a auto%rowtype;
begin
    open pauto('piros');
    loop
        fetch pauto into a;
        exit when pauto%notfound;

        dbms_output.put_line(a.rsz||' '||a.szin);
    end loop;
end;

declare 
    a auto%rowtype;
    sz auto.szin%type;
begin
    dbms_output.put_line('alma: ');
    sz :=:sz;
    for a in (select * from auto where szin=sz) loop
        dbms_output.put_line(a.rsz|| ' '|| a.szin);
    end loop;
end;


--lekérdezni az autokat és kiíratni hogy hány évesek az autók implicit kursorral és explicit cursor
--TO_CHAR(SYSDATE,'YYYY')
--Növelni az autók árát 10%-kkal ha fiatalabb 12 évnél implicittel


declare
    cursor pauto is select * from auto for update of ar; 
    a auto%rowtype;
begin
    open pauto;
    loop
        fetch pauto into a;
        exit when pauto%notfound;
        if to_char(sysdate,'yyyy')-a.evjarat < 16 then
            update auto set ar=ar*1.1 where current of pauto;
        else
            dbms_output.put_line('Idős : '|| a.rsz || ' ' || a.tipus);
        end if;
    end loop;
end;





