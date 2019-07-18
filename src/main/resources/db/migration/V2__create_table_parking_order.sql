create table `parking_order` (
  `order_id` int primary key auto_increment,
  `parking_lot_name` varchar ,
  `car_number` varchar ,
  `create_time` timestamp ,
  `end_time` timestamp ,
  `status` boolean
);