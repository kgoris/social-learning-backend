INSERT INTO student_group (name, code) values ('groupe1.1', 'BA1_XP1');
INSERT INTO student_group (name, code) values ('groupe1.2', 'BA1_XP2');
INSERT INTO student_group (name, code) values ('groupe2', 'BA1_CONTROL');

INSERT INTO student (name, first_name, email, group_id)
	values ('Goris', 'Kévin', 'kevin.goris@student.unamur.be', (select id from student_group where code='BA1_XP1' limit 1));

INSERT INTO student (name, first_name, email, group_id)
	values ('Gokeris', 'Jan', 'goriskevin.ipl@gmail.com', (select id from student_group where code='BA1_XP2' limit 1));

INSERT INTO student (name, first_name, email, group_id)
	values ('Shabani', 'Marleine', 'marleineshabani@gmail.com', (select id from student_group where code='BA1_CONTROL' limit 1));

INSERT INTO student (name, first_name, email, group_id)
	values ('Dupont', 'Hubert', 'goris.kevin.pro@gmail.com', (select id from student_group where code='BA1_CONTROL' limit 1));

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
values (2, 1, 'louer', 'FREE_TEXT', 2, (select id from questionnaire where code='UNIT_19_1'));


INSERT INTO official_answer (id)
values (3);
INSERT INTO question(id, sequence_number, title, type, official_answer_id, questionnaire_id)
values (3, 1, 'je n''en ai pas les moyens', 'MULTIPLE_CHOICE', 3, (select id from questionnaire where code='UNIT_19_1'));
INSERT INTO proposition (official_answer_id, question_id, value)
VALUES (null, 3, 'good value for money');
INSERT INTO proposition (official_answer_id, question_id, value)
VALUES (3, 3, 'good value for money');
INSERT INTO proposition (official_answer_id, question_id, value)
VALUES (null, 3, 'to rent a flat');
INSERT INTO proposition (official_answer_id, question_id, value)
VALUES (null, 3, 'a reasonable amount');


INSERT INTO official_answer (id, value)
values (4, 'true');
INSERT INTO question(id, sequence_number, title, type, official_answer_id, questionnaire_id)
values (4, 1, 'une caution = deposit', 'TRUE_FALSE', 4, (select id from questionnaire where code='UNIT_19_1'));



