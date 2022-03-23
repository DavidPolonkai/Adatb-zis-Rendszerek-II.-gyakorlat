DECLARE
    k number(4);
    oszto integer:=2;
    prim boolean:=true;

BEGIN
    dbms_output.put(:k);
    loop
        if mod(:k,oszto)=0 then
            prim:=false;
        end if;
        oszto:=oszto+1;
        exit when prim=false or oszto>sqrt(:k);
    end loop;
    
    dbms_output.put_line('prímszam: '||case when prim = true then 'igen' else 'nem' end);

END;