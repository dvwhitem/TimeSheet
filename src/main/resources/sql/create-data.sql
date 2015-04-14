-- delete old data
delete from task_employee;
delete from timesheet;
delete from task;
delete from employee;
delete from manager;

-- add few employees
insert into employee values(1, 'management', 'Carlos Graham');
insert into employee values(2, 'management', 'Owen Coleman');
insert into employee values(3, 'engineering', 'Albert Lowrance');
insert into employee values(4, 'engineering', 'Scott Alonso');

-- add few managers
insert into manager values(1, 'Gram Robertson');
insert into manager values(2, 'William Smith');

-- add some tasks
insert into task values(1, false, 'task 1', 1);
insert into task values(2, false, 'task 2', 2);

-- connect tasks to some employees
insert into task_employee values (1, 1);
insert into task_employee values (1, 3);
insert into task_employee values (1, 4);
insert into task_employee values (2, 2);
insert into task_employee values (2, 1);

-- create some timesheets on tasks
insert into timesheet values(1,
	5, -- hours
	1, -- first task
	1 -- employee steve jobs
);

insert into timesheet values(2,
	8, -- hours
	3, -- employee bill gates
  2 -- second task
);