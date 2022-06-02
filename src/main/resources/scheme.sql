CREATE TABLE IF NOT EXISTS userinfo (
  id SERIAL,
  tenant_id INT,
  name VARCHAR(255),
  PRIMARY KEY(id),
  FOREIGN KEY (tenant_id) REFERENCES tenant(id)
);

CREATE TABLE IF NOT EXISTS tenant (
  id SERIAL,
  name VARCHAR(255)
);
