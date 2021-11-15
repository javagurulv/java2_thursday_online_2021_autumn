insert into jg_entertainment.visitors(visitor_name, visitor_surname, visitor_telephone_number)
values ("Name1", "Surname1", "3721");

insert into jg_entertainment.visitors(visitor_name, visitor_surname, visitor_telephone_number)
values ("Name2", "Surname2", "3722");

insert into jg_entertainment.visitors(visitor_name, visitor_surname, visitor_telephone_number)
values ("Name3", "Surname3", "3723");

insert into jg_entertainment.visitors(visitor_name, visitor_surname, visitor_telephone_number)
values ("Name4", "Surname4", "3724");

insert into jg_entertainment.visitors(visitor_id, visitor_name, visitor_surname, visitor_telephone_number)
values (1005, "Name5", "Surname5", "3725");

insert into jg_entertainment.visitors(visitor_id, visitor_name, visitor_surname, visitor_telephone_number)
values (1006, "Name6", "Surname6", "3726");

insert into jg_entertainment.visitors(visitor_id, visitor_name, visitor_surname, visitor_telephone_number)
values (1007, "Name7", "Surname7", "3727");


insert into jg_entertainment.menus(menu_title, menu_description, menu_price)
values ("title1", "description1", "10");

insert into jg_entertainment.menus(menu_title, menu_description, menu_price)
values ("title2", "description2", "20");

insert into jg_entertainment.menus(menu_id, menu_title, menu_description, menu_price)
values (1003, "title3", "description3", "30");


insert into jg_entertainment.tables(table_title, table_capacity, table_price)
values ("title1", "4", "80");

insert into jg_entertainment.tables(table_title, table_capacity, table_price)
values ("title2", "4", "80");

insert into jg_entertainment.tables(table_title, table_capacity, table_price)
values ("title3", "6", "110");

insert into jg_entertainment.tables(table_id, table_title, table_capacity, table_price)
values (1004, "title4", "6", "110");

insert into jg_entertainment.reservations(id_visitor, id_table, id_menu, reservation_date)
values (1002, 1001, 1001, '2024-01-02 19:00');

insert into jg_entertainment.reservations(id_visitor, id_table, id_menu, reservation_date)
values (1003, 1003, 1002, '2024-01-01 19:00');

insert into jg_entertainment.reservations(id_visitor, id_table, id_menu, reservation_date)
values (1004, 1001, 1003, '2024-02-01 19:00');
