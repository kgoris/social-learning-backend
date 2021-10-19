INSERT INTO student_group (name, code) values ('groupe1.1', 'BA1_XP1');
INSERT INTO student_group (name, code) values ('groupe1.2', 'BA1_XP2');
INSERT INTO student_group (name, code) values ('groupe2', 'BA1_CONTROL');

INSERT INTO student (name, first_name, username, group_id)
	values ('Goris', 'Kévin', 'kevin.goris.unamur',  (select id from student_group where code='BA1_XP1' limit 1));

INSERT INTO student (name, first_name, username, group_id)
	values ('Gokeris', 'Jan', 'kevin.goris.prive', (select id from student_group where code='BA1_XP2' limit 1));

INSERT INTO student (name, first_name, username, group_id)
	values ('Shabani', 'Marleine', 'marleine.shabani', (select id from student_group where code='BA1_CONTROL' limit 1));

INSERT INTO student (name, first_name, username, group_id)
	values ('Dupont', 'Hubert', 'hubert.dupont', (select id from student_group where code='BA1_CONTROL' limit 1));

INSERT INTO questionnaire(title, level_sequence, code) values ('unit 19.1', 1, 'UNIT_19_1');
INSERT INTO questionnaire(title, level_sequence, code) values ('unit 19.2', 1, 'UNIT_19_2');
INSERT INTO questionnaire(title, level_sequence, code) values ('unit 65', 2, 'UNIT_65');

INSERT INTO access (access_type, questionnaire_id, student_group_id)
    values ('WORK',
            (select id from questionnaire where code='UNIT_19_1'),
            (select id from student_group where code='BA1_XP1')
            );

INSERT INTO access (access_type, questionnaire_id, student_group_id)
    values ('WORK',
            (select id from questionnaire where code='UNIT_19_2'),
            (select id from student_group where code='BA1_XP2')
    );

INSERT INTO access (access_type, questionnaire_id, student_group_id)
    values ('WORK',
            (select id from questionnaire where code='UNIT_65'),
            (select id from student_group where code='BA1_CONTROL')
    );

INSERT INTO access (access_type, questionnaire_id, student_group_id)
    values ('OBSERVE',
            (select id from questionnaire where code='UNIT_19_1'),
            (select id from student_group where code='BA1_XP2')
    );

INSERT INTO access (access_type, questionnaire_id, student_group_id)
    values ('OBSERVE',
            (select id from questionnaire where code='UNIT_19_2'),
            (select id from student_group where code='BA1_XP1')
    );



INSERT INTO official_answer (id, value)
values (1, 'a reasonable amount');
INSERT INTO question(id, sequence_number, title, type, official_answer_id, questionnaire_id)
values (1, 1, 'un montant raisonnable', 'FREE_TEXT', 1, (select id from questionnaire where code='UNIT_19_1'));


INSERT INTO official_answer (id, value)
values (2, 'to rent');
INSERT INTO question(id, sequence_number, title, type, official_answer_id, questionnaire_id)
values (2, 2, 'louer', 'FREE_TEXT', 2, (select id from questionnaire where code='UNIT_19_1'));


INSERT INTO official_answer (id)
values (3);

INSERT INTO question(id, sequence_number, title, type, official_answer_id, questionnaire_id)
values (3, 3, 'je n''en ai pas les moyens', 'MULTIPLE_CHOICE', 3, (select id from questionnaire where code='UNIT_19_1'));
INSERT INTO proposition (official_answer_id, question_id, value)
VALUES (null, 3, 'good value for money');
INSERT INTO proposition (official_answer_id, question_id, value)
VALUES (3, 3, 'I can''t afford that ');
INSERT INTO proposition (official_answer_id, question_id, value)
VALUES (null, 3, 'to rent a flat');
INSERT INTO proposition (official_answer_id, question_id, value)
VALUES (null, 3, 'a reasonable amount');

INSERT INTO official_answer (id, value)
values (4, 'true');
INSERT INTO question(id, sequence_number, title, type, official_answer_id, questionnaire_id)
values (4, 4, 'une caution = deposit', 'TRUE_FALSE', 4, (select id from questionnaire where code='UNIT_19_1'));


INSERT INTO sentence (id, first_part, expression, second_part, language)
values (1, 'We can''t', 'afford', 'a large house', 'ENGLISH');
INSERT INTO sentence (id, first_part, expression, second_part, language)
values (2, 'Nous ne pouvons pas nous', 'offrir', 'une grande maison', 'FRENCH');

INSERT INTO sentence (id, first_part, expression, second_part, language)
values (3, 'You need an equal', 'amount', 'of flour and sugar', 'ENGLISH');
INSERT INTO sentence (id, first_part, expression, second_part, language)
values (4, 'Vous avez besoin de la même', 'quantité', 'de farine et de sucre', 'FRENCH');

INSERT INTO sentence (id, first_part, expression, second_part, language)
values (5, 'Do you own your home or do you', 'rent', '?', 'ENGLISH');
INSERT INTO sentence (id, first_part, expression, second_part, language)
values (6, 'Est-ce que tu possèdes ta maison ou est-ce que tu', 'loues', '?', 'FRENCH');

INSERT INTO sentence (id, first_part, expression, second_part, language)
values (7, 'The bank statement shows', 'deposits', 'in one column and withdrawals in another.', 'ENGLISH');
INSERT INTO sentence (id, first_part, expression, second_part, language)
values (8, 'Le relevé de banque montre les', 'dépôts', 'dans une colonne et les retraits dans une autre.', 'FRENCH');

INSERT INTO learning_item (original_id, translation_id, questionnaire_id, sequence_number)
values (1, 2, (select id from questionnaire where code='UNIT_19_1'), 1);

INSERT INTO learning_item (original_id, translation_id, questionnaire_id, sequence_number)
values (3, 4, (select id from questionnaire where code='UNIT_19_1'), 2);

INSERT INTO learning_item (original_id, translation_id, questionnaire_id, sequence_number)
values (5, 6, (select id from questionnaire where code='UNIT_19_1'), 3);

INSERT INTO learning_item (original_id, translation_id, questionnaire_id, sequence_number)
values (7, 8, (select id from questionnaire where code='UNIT_19_1'), 4);


