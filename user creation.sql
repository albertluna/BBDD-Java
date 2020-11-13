CREATE USER 'analytic_user', 'manager_user', 'rrhh_user';

/**************************************************/

GRANT SELECT, SHOW DATABASES, CREATE VIEW, SHOW VIEW 
ON OLAP
TO ‘analytic_user’;

/**************************************************/
GRANT SELECT, UPDATE, INSERT, DELETE
ON OLAP
TO ‘manager_user’;

GRANT SELECT, UPDATE, INSERT, DELETE	 
ON OLTP
TO ‘manager_user’;
/**************************************************/

GRANT CREATE USER 
ON here
TO ‘rrhh_user’;