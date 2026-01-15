create table if not exists example_item (
  id uuid primary key,
  name varchar(120) not null,
  created_at timestamp not null default current_timestamp
);

create index if not exists idx_example_item_name on example_item (name);
