insert into category(name, description, count) values  ('car', 'vehicle', 4),('service liquids', 'engine oil, transmission oil', 2),('parts', 'parts for car repair', 10),('accessories', 'diferent accessories for car', 4),('fuel', 'fuel for car', 3);
insert into product(name, categoryId, description, imageSrc, price, count) VALUES ('arkana', 1, 'Renault', 'no image', 1100000.0, 5.0),('Audi A5', 1, 'Audi', 'no image', 2200000.0, 1.0),('Duster', 1, 'Renault', 'no image', 900000.0, 10.0),('Creta', 1, 'Huyndai', 'no image', 1000000.0, 4.0),('engine oil', 2, 'Castrol 0W-30', 'no image', 570.0, 350.0),('transmission oil', 2, 'Castrol transmission 5W-50', 'no image', 610.0, 120.0),('right door Arkana', 3, 'rigth front door for Arkana', 'no image', 10000.0, 2.0),('left door Arkana', 3, 'left front door for Arkana', 'no image', 9000.0, 1.0),('oil filter for 2.0 F4R', 3, 'oil filter', 'no image', 500.0, 15.0),('air filter for F4R', 3, 'air filter', 'no image', 1120.0, 11.0),('crankshaft F4R', 3, 'crankshaft', 'no image', 19000.0, 1.0),('oil filter TFSI 2.0', 3, 'oil filter', 'no image', 650.0, 5.0),('EGR valve TFSI 2.0', 3, 'EGR valve', 'no image', 1500.0, 4.0),('valve TFSI 2.0', 3, 'valve timing mechanism', 'no image', 900.0, 16.0),('windscreen Arkana', 3, 'windscreen', 'no image', 25000.0, 1.0),('windscreen Duster', 3, 'windscreen', 'no image', 22000.0, 4.0);
insert into "users"(first_Name, last_name, login, hashpassword, role, status) values ('Egor', 'Klepikov', 'ken', '$2y$12$o4lnmR3uThv70g3exwrwve/GC58MrnEyskz5UhU45.C7NMKJHnumy', 'ADMIN', 'ACTIVE');