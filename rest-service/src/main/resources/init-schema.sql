INSERT INTO customer (ic,cx_age,cxdob,cx_name) 
SELECT '17a534362135d1502e439de3c495192ce8fce6edd68f13234bc9bd38309ef4ff',23,'1990-02-02','Sam'
WHERE NOT EXISTS (select ic from customer WHERE ic = '17a534362135d1502e439de3c495192ce8fce6edd68f13234bc9bd38309ef4ff');

INSERT INTO customer (ic,cx_age,cxdob,cx_name) 
SELECT '2ded9cc7842563eeb3ecf4da66d91f95f13796ce40dcc16d1364ff11c226d7c8',27,'1995-02-02','Alice'
WHERE NOT EXISTS (select ic from customer WHERE ic = '2ded9cc7842563eeb3ecf4da66d91f95f13796ce40dcc16d1364ff11c226d7c8');

INSERT INTO customer (ic,cx_age,cxdob,cx_name) 
SELECT '5c49b9a5a7ea927c7b86299d179cda0277e54e92d09e68e57b15a021b4532b1d',28,'1990-02-02','Lisa'
WHERE NOT EXISTS (select ic from customer WHERE ic = '5c49b9a5a7ea927c7b86299d179cda0277e54e92d09e68e57b15a021b4532b1d');
