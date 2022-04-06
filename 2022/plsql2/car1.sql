select * from car

alter table car add(kor number(2));

alter table car modify(year number(10));


declare 
    new_year number(2);
    color varchar2(10);
begin
    update car set year=:new_year where color=:color;
end;
