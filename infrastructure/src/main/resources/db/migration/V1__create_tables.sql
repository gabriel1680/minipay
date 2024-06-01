CREATE TABLE users (
    id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    type VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    document_type VARCHAR(255) NOT NULL,
    document_value VARCHAR(255) UNIQUE NOT NULL,
    balance DECIMAL(10, 2) NOT NULL DEFAULT 0.00
);

CREATE TABLE transfers (
    id SERIAL PRIMARY KEY,
    payer_id UUID NOT NULL,
    payee_id UUID NOT NULL,
    amount DECIMAL(10, 2) NOT NULL,
    CONSTRAINT fk_payer FOREIGN KEY (payer_id) REFERENCES users (id),
    CONSTRAINT fk_payee FOREIGN KEY (payee_id) REFERENCES users (id)
);