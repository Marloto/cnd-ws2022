# Bad Java Server Example

Simple employee management website.

## Dependencies

- MySQL Connector
- Servlet API
- Jetty

(see libs-Folder)

## Requires

- MySQL DB on localhost (Using docker: `docker run --name some-mysql -e MYSQL_ROOT_PASSWORD=master -e MYSQL_USER=example -e MYSQL_PASSWORD=example -e MYSQL_DATABASE=example -d -p 3306:3306 mysql:8`)
- Database User (add to DatabaseConnection or use root:root)
- Database (add to DatabaseConnection or use example)
- Table: `create table employees(id int(10), name varchar(20));` 

