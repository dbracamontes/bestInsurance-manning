--liquibase formatted sql
--changeset yourname:001-changeset1.sql splitStatements:false

CREATE TABLE countries(
	country_id UUID PRIMARY KEY,
	name VARCHAR( 64 ) NOT NULL,
	population INTEGER
);

CREATE TABLE states(
	state_id UUID PRIMARY KEY,
	country_id UUID NOT NULL,
	name VARCHAR( 64 ) NOT NULL,
	population INTEGER,
	CONSTRAINT fk_country
      FOREIGN KEY(country_id) 
	  REFERENCES countries(country_id)
);

CREATE TABLE cities(
	city_id UUID PRIMARY Key,
	country_id UUID NOT NULL,
	state_id UUID,
	name VARCHAR( 64 ) NOT NULL,
	population INTEGER,
	CONSTRAINT fk_country
		FOREIGN KEY(country_id)
		REFERENCES countries(country_id),
	CONSTRAINT fk_state
		FOREIGN KEY(state_id)
		REFERENCES states(state_id)
);

CREATE TABLE addresses(
	address_id UUID PRIMARY KEY,
	country_id UUID NOT NULL,
	city_id UUID NOT NULL,
	state_id UUID,
	address VARCHAR(128) NOT NULL,
	postal_code VARCHAR(16),
		CONSTRAINT fk_country
			FOREIGN KEY(country_id)
			REFERENCES countries(country_id),
		CONSTRAINT fk_city
			FOREIGN KEY(city_id)
			REFERENCES cities(city_id),
		CONSTRAINT fk_state
			FOREIGN KEY(state_id)
			REFERENCES states(state_id)
);

CREATE TABLE customers(
	customer_id UUID PRIMARY KEY,
	name  VARCHAR ( 64 ) NOT NULL,
	surname  VARCHAR ( 64 ) NOT NULL,
	email  VARCHAR ( 320 ) NOT NULL,
	created TIMESTAMP WITH TIME ZONE  NOT NULL,
	updated TIMESTAMP WITH TIME ZONE  NOT NULL,
	address_id UUID,
		CONSTRAINT fk_address
			FOREIGN KEY(address_id)
			REFERENCES addresses(address_id)
);


CREATE TABLE coverages(
	coverage_id UUID PRIMARY KEY,
	name VARCHAR(16) NOT NULL,
	description TEXT
);

CREATE TABLE policies(
	policy_id UUID PRIMARY KEY,
	name VARCHAR(16) NOT NULL,
	description TEXT,
	price numeric(4,2) NOT NULL,
	created TIMESTAMP WITH TIME ZONE  NOT NULL,
	updated TIMESTAMP WITH TIME ZONE  NOT NULL
);

CREATE TABLE policies_coverages(
	coverage_id UUID NOT NULL,
	policy_id UUID NOT NULL,
		CONSTRAINT fk_coverage
			FOREIGN KEY(coverage_id)
			REFERENCES coverages(coverage_id),
		CONSTRAINT fk_policies
			FOREIGN KEY(policy_id)
			REFERENCES policies(policy_id)
);

CREATE TABLE subscriptions(
	policy_id UUID NOT NULL,
	customer_id UUID NOT NULL,
	start_date DATE NOT NULL,
	end_date DATE NOT NULL,
	paid_price NUMERIC(4,2) NOT NULL,
	created TIMESTAMP WITH TIME ZONE  NOT NULL,
	updated TIMESTAMP WITH TIME ZONE  NOT NULL,
	PRIMARY KEY(policy_id, customer_id),
		CONSTRAINT fk_policy
			FOREIGN KEY(policy_id)
			REFERENCES policies(policy_id),
		CONSTRAINT fk_customer
			FOREIGN KEY(customer_id)
			REFERENCES customers(customer_id)
);