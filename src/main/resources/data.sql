INSERT INTO users(nome, email, senha, criado_em) VALUES ('Rodrigo Miguel', 'rodrigo@gmail.com', '$2a$10$4bzNdzS/0lrxpqlbeuhI..bkOB2AB7x4fd4.da4U7CEAg.FOJdVAi', now());
INSERT INTO users(nome, email, senha, criado_em) VALUES ('Isabela Prado', 'isabela@gmail.com', '$2a$10$4bzNdzS/0lrxpqlbeuhI..bkOB2AB7x4fd4.da4U7CEAg.FOJdVAi', now());

INSERT INTO formacoes(nome) VALUES ('4-4-2'), ('4-3-3'), ('3-4-3');

INSERT INTO times(nome, pontos, formacao_id, user_id, criado_em) VALUES ('LambaiaFC', 0, 1, 1, now()), ('DeusEhMaisFC', 0, 2, 2, now());

INSERT INTO paises(nome, sigla) VALUES ('Qatar', 'QA'), ('Equador', 'EC'), ('Senegal', 'SN'), ('Holanda', 'NL'),
                                               ('Inglaterra', 'EN'), ('Irã', 'IR'), ('Estados Unidos', 'US'), ('País de Gales', 'Wa'),
                                               ('Argentina', 'AR'), ('Arábia Saudita', 'SA'), ('México', 'MX'), ('Polônia', 'PL'),
                                               ('França', 'FR'), ('Austrália', 'AU'), ('Dinamarca', 'DK'), ('Tunísia', 'TN'),
                                               ('Espanha', 'ES'), ('Costa Rica', 'CR'), ('Alemanha', 'DE'), ('Japão', 'JP'),
                                               ('Bélgica', 'BE'), ('Canadá', 'CA'), ('Marrocos', 'MA'), ('Croácia', 'HR'),
                                               ('Brasil', 'BR'), ('Sérvia', 'RS'), ('Suíça', 'CH'), ('Camarões', 'CM'),
                                               ('Portugal', 'PT'), ('Gana', 'GH'), ('Uruguai', 'UY'), ('Coréia do Sul', 'KR');

INSERT INTO posicoes(nome) VALUES ('Goleiro'), ('Defesa'), ('Meio-Campo'), ('Atacante');

INSERT INTO jogadores(nome, pontos, pais_id, posicao_id) VALUES ('Sadio Mané', 0, 3, 4),
                                                                 ('Neymar Jr', 0, 25, 4),
                                                                 ('Lionel Messi', 0, 9, 4),
                                                                 ('Alisson Becker', 0, 25, 1),
                                                                 ('Hugo Lorris', 0, 13, 1),
                                                                 ('Sergio Romero', 0, 9, 1),
                                                                 ('Angel Di Maria', 0, 9, 4),
                                                                 ('Ngolo Kanté', 0, 13, 3),
                                                                 ('Kylian Mbappé', 0, 13, 4),
                                                                 ('Raphael Varane', 0, 13, 2),
                                                                 ('Thiago Silva', 0, 25, 2),
                                                                 ('Kyle Walker', 0, 5, 2),
                                                                 ('Jorden Henderson', 0, 5, 3),
                                                                 ('John Stones', 0, 5, 2),
                                                                 ('Trent Alexander-Arnold', 0, 5, 2),
                                                                 ('Toni Kross', 0, 19, 3),
                                                                 ('Kevin De Bruyne', 0, 21, 3),
                                                                 ('Bernardo Silva', 0, 29, 3),
                                                                 ('Diogo Jota', 0, 29, 4),
                                                                 ('Cristiano Ronaldo', 0, 29, 4),
                                                                 ('Son Heung-min', 0, 32, 4),
                                                                 ('Virgil van Dijk', 0, 4, 2),
                                                                 ('Luka Modrić', 0, 24, 3),
                                                                 ('Casemiro', 0, 25, 3);
                                                                 