create table products
(
    id         bigserial primary key,
    name      text  not null,
    price      int not null,
    image_link text
);

---

create table category
(
    id        bigserial primary key,
    name      text not null,
    alias     text not null,
    parent_id int,

    foreign key (parent_id) references category (id)
);

create index category_parent_id_idx on category (parent_id);

---

create table product_category
(
    products_id  bigint not null,
    category_id bigint not null,

    primary key (products_id, category_id),
    foreign key (products_id) references products (id),
    foreign key (category_id) references category (id)
)
