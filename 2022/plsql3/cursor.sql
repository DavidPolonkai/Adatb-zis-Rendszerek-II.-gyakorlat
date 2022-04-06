declare
    a auto%rowtype;
    r auto.rsz%type:='AAA000';
    darab number(10);
begin
    select * into a from auto where rsz=r;
    dbms_output.put_line(a.rsz ||' ' ||a.tipus||' ' ||a.szin);
    select count(*) into darab from auto;
    dbms_output.put_line('Az autok száma: '||darab);
end;

---explicit
declare 
    CURSOR sauto is select * from auto;
    a auto%rowtype;
begin
    open sauto;
    loop
        fetch sauto into a;
        exit when sauto%notfound;
        dbms_output.put_line(a.rsz||' '||a.evjarat|| ' ' || a.tipus);
    end loop;
end;

--implicit
declare 
    a auto%rowtype;
begin
    for a in (select * from auto) loop
        dbms_output.put_line(a.rsz||' '||a.tipus);
    end loop;
end;
