ALTER table products
    ADD COLUMN IF NOT EXISTS image_link text;

ALTER table category
    ADD COLUMN IF NOT EXISTS alias text not null DEFAULT 'foo',
    ADD COLUMN if not exists parent_id int,
    DROP CONSTRAINT IF EXISTS fk_index;
ALTER table category
    ADD CONSTRAINT fk_index foreign key (parent_id) references category (id);

create index IF NOT EXISTS category_parent_id_idx on category (parent_id);

create table IF NOT EXISTS product_category
(
    products_id  bigint not null,
    category_id bigint not null,

    primary key (products_id, category_id),
    foreign key (products_id) references products (id),
    foreign key (category_id) references category (id)
)