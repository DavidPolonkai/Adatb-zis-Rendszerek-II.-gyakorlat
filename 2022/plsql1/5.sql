DECLARE
 l_seed char(100);
 r number(4);
BEGIN
    l_seed := to_char(SYSTIMESTAMP,'YYYYDDMMHH24MISSFFFF');
    dbms_random.seed(l_seed);
    r := dbms_random.value(0,8);
    dbms_output.put_line(r||'/4=');
    case mod(r,4)
        when 1 then dbms_output.put_line(trunc(r/4.0)||' 1 maradék ');
        when 2 then dbms_output.put_line(trunc(r/4.0)||' 2 maradék ');
        when 3 then dbms_output.put_line(trunc(r/4.0)||' 3 maradék ');
        else
         dbms_output.put_line(trunc(r/4.0)||' 0 maradék');
    end case;
END;