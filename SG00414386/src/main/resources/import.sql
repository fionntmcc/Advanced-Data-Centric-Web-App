INSERT INTO garage (gid, location, budget) VALUES ("G1", "Galway", 500000);
INSERT INTO garage (gid, location, budget) VALUES ("G2", "Ballinasloe", 335000);
INSERT INTO garage (gid, location, budget) VALUES ("G3", "Ballina", 222300);

INSERT INTO mechanic (mid, name, salary, garage_id) VALUES ("M001", "Michael", 55000.00, 1);
INSERT INTO mechanic (mid, name, salary, garage_id) VALUES ("M002", "Bill", 53223.99, 1);
INSERT INTO mechanic (mid, name, salary, garage_id) VALUES ("M003", "Anne", 51000.01, 2);
INSERT INTO mechanic (mid, name, salary, garage_id) VALUES ("M004", "Thomas", 48300.00, 2);
INSERT INTO mechanic (mid, name, salary, garage_id) VALUES ("M005", "Pat", 55000, 3);
INSERT INTO mechanic (mid, name, salary, garage_id) VALUES ("M006", "Sean", 54000, 1);

INSERT INTO customer (cid, name, phone) VALUES ("C0001", "Mary Jones", "091 1234523");
INSERT INTO customer (cid, name, phone) VALUES ("C0002", "Anthony Coleman", "091 8982233");
INSERT INTO customer (cid, name, phone) VALUES ("C0003", "Bertie Wynne", "096 7784512");
INSERT INTO customer (cid, name, phone) VALUES ("C0004", "John Flynn", "094 7788233");
INSERT INTO customer (cid, name, phone) VALUES ("C0005", "Alice O'Connor", "091 7727273");

INSERT INTO vehicle (reg, make, model, owner_id, mechanic_id) VALUES ("09-G-992", "Toyota", "Corolla", 1, 1);
INSERT INTO vehicle (reg, make, model, owner_id, mechanic_id) VALUES ("221-RN-8892", "Toyota", "Yaris", 1, 2);
INSERT INTO vehicle (reg, make, model, owner_id, mechanic_id) VALUES ("192-G-43", "Nissan", "Leaf", 2, 2);
INSERT INTO vehicle (reg, make, model, owner_id, mechanic_id) VALUES ("201-G-93232", "VW", "Tiguan", 3, 3);
INSERT INTO vehicle (reg, make, model, owner_id, mechanic_id) VALUES ("222-CE-87", "Toyota", "Corolla", 4, 4);
INSERT INTO vehicle (reg, make, model, owner_id, mechanic_id) VALUES ("242-MO-2289", "VW", "Golf", 4, 5);
INSERT INTO vehicle (reg, make, model, owner_id, mechanic_id) VALUES ("251-RN-103", "Nissan", "Qashqai", 5, 5);
