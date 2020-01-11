INSERT INTO user_db.customer (ic,cx_age,cxdob,cx_name) 
SELECT 'testic1',23,'1990-02-02','Sam'
WHERE NOT EXISTS (select ic from customer WHERE ic = 'testic1');

INSERT INTO user_db.customer (ic,cx_age,cxdob,cx_name) 
SELECT 'testic2',27,'1995-02-02','Alice'
WHERE NOT EXISTS (select ic from customer WHERE ic = 'testic2');

INSERT INTO user_db.customer (ic,cx_age,cxdob,cx_name) 
SELECT 'testic3',28,'1990-02-02','Lisa'
WHERE NOT EXISTS (select ic from customer WHERE ic = 'testic3');
