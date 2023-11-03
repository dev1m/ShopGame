-- phpMyAdmin SQL Dump
-- version 5.1.3
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th10 03, 2023 lúc 06:03 AM
-- Phiên bản máy phục vụ: 10.4.24-MariaDB
-- Phiên bản PHP: 7.4.29

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `do_an_java`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `accounts`
--

CREATE TABLE `accounts` (
  `id` int(11) NOT NULL,
  `group_id` int(11) DEFAULT NULL,
  `username_acc` varchar(255) DEFAULT NULL,
  `password_acc` varchar(255) DEFAULT NULL,
  `detail` text DEFAULT NULL,
  `date_submit` datetime DEFAULT NULL,
  `date_sell` datetime DEFAULT NULL,
  `img` varchar(255) DEFAULT NULL,
  `money` int(11) DEFAULT NULL,
  `list_img` longtext DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `accounts`
--

INSERT INTO `accounts` (`id`, `group_id`, `username_acc`, `password_acc`, `detail`, `date_submit`, `date_sell`, `img`, `money`, `list_img`, `user_id`) VALUES
(27, 8, 'username123', 'password123', 'Đăng nhập: Facebook\r\nPet: Có', '2023-10-05 23:03:05', '2023-10-06 00:36:30', 'avt_ff.jpg', 200000, '5bcbd2c3b7a49580065bff69372c56c9.jpg,b1a4a9eaa170db79c06cf7ed65c7a173.jpg,60d3de3fac99d194eff7ea64361fabb8.jpg,334130e33895fe089cc451968963068d.jpg,336a8273c80393edabf995a5c009dfd4.jpg,595becef3d993bfb6a83d93831849218.jpg,c9d34b43d6f43df1e29561da0a981c6c.jpg,fc14b55ff7b439c322e42bb6f4b542e4.jpg', 7),
(28, 8, 'username123', 'password123', 'Thông tin 1: 123\r\nThông tin 2: 234', '2023-10-06 23:45:51', '2023-10-09 23:37:11', 'avt_ff.jpg', 200000, '5bcbd2c3b7a49580065bff69372c56c9.jpg,b1a4a9eaa170db79c06cf7ed65c7a173.jpg,60d3de3fac99d194eff7ea64361fabb8.jpg,334130e33895fe089cc451968963068d.jpg,336a8273c80393edabf995a5c009dfd4.jpg,595becef3d993bfb6a83d93831849218.jpg,c9d34b43d6f43df1e29561da0a981c6c.jpg,fc14b55ff7b439c322e42bb6f4b542e4.jpg', 7),
(29, 8, 'username', 'password', 'Thông tin1: 123\r\nThông tin 2: 234', '2023-10-06 23:46:29', '2023-10-18 15:32:10', 'avt_ff.jpg', 600000, '5bcbd2c3b7a49580065bff69372c56c9.jpg,b1a4a9eaa170db79c06cf7ed65c7a173.jpg,60d3de3fac99d194eff7ea64361fabb8.jpg,334130e33895fe089cc451968963068d.jpg,336a8273c80393edabf995a5c009dfd4.jpg,595becef3d993bfb6a83d93831849218.jpg,c9d34b43d6f43df1e29561da0a981c6c.jpg,fc14b55ff7b439c322e42bb6f4b542e4.jpg', 7),
(30, 8, 'tk1', 'mk2', 'Log: Facebook\r\nPet: Có', '2023-10-18 14:56:39', NULL, 'avt_ff.jpg', 150000, '5bcbd2c3b7a49580065bff69372c56c9.jpg,b1a4a9eaa170db79c06cf7ed65c7a173.jpg,60d3de3fac99d194eff7ea64361fabb8.jpg,334130e33895fe089cc451968963068d.jpg,336a8273c80393edabf995a5c009dfd4.jpg,595becef3d993bfb6a83d93831849218.jpg,c9d34b43d6f43df1e29561da0a981c6c.jpg,fc14b55ff7b439c322e42bb6f4b542e4.jpg', NULL),
(31, 8, 'tk1', 'mk1', '', '2023-10-26 14:36:12', NULL, 'avt_ff.jpg', 20000, '5bcbd2c3b7a49580065bff69372c56c9.jpg,b1a4a9eaa170db79c06cf7ed65c7a173.jpg,60d3de3fac99d194eff7ea64361fabb8.jpg,334130e33895fe089cc451968963068d.jpg,336a8273c80393edabf995a5c009dfd4.jpg,595becef3d993bfb6a83d93831849218.jpg,c9d34b43d6f43df1e29561da0a981c6c.jpg,fc14b55ff7b439c322e42bb6f4b542e4.jpg', NULL),
(32, 8, 'tk1', 'mk1', '', '2023-10-26 14:36:28', '2023-10-26 16:50:23', 'avt_ff.jpg', 25000, '5bcbd2c3b7a49580065bff69372c56c9.jpg,b1a4a9eaa170db79c06cf7ed65c7a173.jpg,60d3de3fac99d194eff7ea64361fabb8.jpg,334130e33895fe089cc451968963068d.jpg,336a8273c80393edabf995a5c009dfd4.jpg,595becef3d993bfb6a83d93831849218.jpg,c9d34b43d6f43df1e29561da0a981c6c.jpg,fc14b55ff7b439c322e42bb6f4b542e4.jpg', 7),
(33, 8, 'tk1', 'mk13', '', '2023-10-26 14:36:59', NULL, 'avt_ff.jpg', 30000, '60d3de3fac99d194eff7ea64361fabb8.jpg,334130e33895fe089cc451968963068d.jpg', NULL),
(34, 8, 'tk1', 'password123', '', '2023-10-26 14:38:39', NULL, 'avt_ff.jpg', 80000, 'b1a4a9eaa170db79c06cf7ed65c7a173.jpg', NULL),
(35, 8, 'username123', 'password123', '', '2023-10-26 14:38:55', NULL, 'avt_ff.jpg', 65000, '334130e33895fe089cc451968963068d.jpg,336a8273c80393edabf995a5c009dfd4.jpg', NULL),
(36, 8, 'username123', 'password123', '', '2023-10-26 14:39:16', NULL, 'avt_ff.jpg', 34000, '5bcbd2c3b7a49580065bff69372c56c9.jpg,b1a4a9eaa170db79c06cf7ed65c7a173.jpg,60d3de3fac99d194eff7ea64361fabb8.jpg,334130e33895fe089cc451968963068d.jpg,336a8273c80393edabf995a5c009dfd4.jpg', NULL),
(37, 8, 'username123', 'password123', '', '2023-10-26 14:39:36', NULL, 'avt_ff.jpg', 12000, '60d3de3fac99d194eff7ea64361fabb8.jpg,334130e33895fe089cc451968963068d.jpg,336a8273c80393edabf995a5c009dfd4.jpg', NULL),
(38, 8, 'tk1', 'mk1', '', '2023-10-26 14:39:54', NULL, 'avt_ff.jpg', 23000, '60d3de3fac99d194eff7ea64361fabb8.jpg,334130e33895fe089cc451968963068d.jpg,336a8273c80393edabf995a5c009dfd4.jpg', NULL);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `banks`
--

CREATE TABLE `banks` (
  `id` int(11) NOT NULL,
  `bank_number` text NOT NULL,
  `bank_name` text NOT NULL,
  `bank_fullname` text NOT NULL,
  `bank_logo` text NOT NULL,
  `bank_note` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `banks`
--

INSERT INTO `banks` (`id`, `bank_number`, `bank_name`, `bank_fullname`, `bank_logo`, `bank_note`) VALUES
(1, '2221102686868', 'MB BANK', 'PHẠM VĂN ĐOÀN', 'mb.jpg', 'Vui lòng nhập đúng nội dung khi chuyển khoản. \r\n                                    '),
(3, '0394278865', 'MOMO', 'PHẠM VĂN ĐOÀN', 'momo.png', 'Vui lòng nhập đúng nội dung khi chuyển khoản.       ');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `bank_auto`
--

CREATE TABLE `bank_auto` (
  `id` int(11) NOT NULL,
  `transaction_id` varchar(100) NOT NULL,
  `amount` int(11) NOT NULL DEFAULT 0,
  `description` text NOT NULL,
  `time` datetime NOT NULL,
  `user_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `cards`
--

CREATE TABLE `cards` (
  `id` int(11) NOT NULL,
  `card_code` varchar(255) NOT NULL,
  `card_type` varchar(255) NOT NULL,
  `card_price` int(11) NOT NULL,
  `card_real_price` int(11) NOT NULL DEFAULT 0,
  `card_seri` text NOT NULL,
  `card_pin` text NOT NULL,
  `create_date` datetime NOT NULL,
  `card_status` varchar(50) NOT NULL,
  `card_note` text NOT NULL,
  `user_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `cash_flow`
--

CREATE TABLE `cash_flow` (
  `id` int(11) NOT NULL,
  `cash_old` int(11) NOT NULL,
  `cash_change` int(11) NOT NULL,
  `cash_new` int(11) NOT NULL,
  `time` datetime NOT NULL,
  `cash_note` text NOT NULL,
  `user_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `cash_flow`
--

INSERT INTO `cash_flow` (`id`, `cash_old`, `cash_change`, `cash_new`, `time`, `cash_note`, `user_id`) VALUES
(24, 550000, 200000, 350000, '2023-10-09 23:37:11', 'Mua tài khoản (# 28)', 7),
(25, 350000, 50000, 300000, '2023-10-09 23:37:50', 'Đặt hàng gói (# 6)', 7),
(26, 300000, 50000, 350000, '2023-10-09 23:38:50', 'ADMIN cộng tiền (\'\')', 7),
(27, 350000, 1000000, 1350000, '2023-10-18 15:31:55', 'ADMIN cộng tiền (\'\')', 7),
(28, 1350000, 600000, 750000, '2023-10-18 15:32:10', 'Mua tài khoản (# 29)', 7),
(29, 750000, 50000, 700000, '2023-10-18 15:34:22', 'Đặt hàng gói (# 6)', 7),
(30, 700000, 25000, 675000, '2023-10-26 16:50:23', 'Mua tài khoản (# 32)', 7),
(31, 675000, 50000, 625000, '2023-10-26 22:43:52', 'Đặt hàng gói (# 6)', 7);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `category`
--

CREATE TABLE `category` (
  `id` int(11) NOT NULL,
  `title` varchar(255) NOT NULL,
  `display` varchar(25) NOT NULL,
  `img` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `category`
--

INSERT INTO `category` (`id`, `title`, `display`, `img`) VALUES
(10, 'DANH MỤC LIÊN MINH', 'SHOW', 'dm_lm.png'),
(11, 'DANH MỤC LIÊN QUÂN', 'SHOW', 'dm_lq.png'),
(12, 'DANH MỤC FREE FIRE', 'SHOW', 'dm_ff.png'),
(13, 'DANH MỤC PUBG ', 'SHOW', 'dm_pubg.png');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `category_caythue`
--

CREATE TABLE `category_caythue` (
  `id` int(11) NOT NULL,
  `title` varchar(255) NOT NULL,
  `display` varchar(25) NOT NULL,
  `img` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `category_caythue`
--

INSERT INTO `category_caythue` (`id`, `title`, `display`, `img`) VALUES
(9, 'DANH MỤC CÀY THUÊ PUBG', 'SHOW', 'ct_pubg.png'),
(10, 'DANH MỤC CÀY THUÊ TỐC CHIẾN', 'SHOW', 'ct_tc.png'),
(11, 'DANH MỤC CÀY THUÊ LIÊN QUÂN', 'SHOW', 'ct_lq.png'),
(12, 'DANH MỤC CÀY THUÊ LIÊN MINH', 'SHOW', 'ct_lm.png');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `groups`
--

CREATE TABLE `groups` (
  `id` int(11) NOT NULL,
  `category_id` int(11) NOT NULL,
  `title` varchar(255) NOT NULL,
  `display` varchar(25) NOT NULL,
  `img` varchar(255) NOT NULL,
  `description` longtext NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `groups`
--

INSERT INTO `groups` (`id`, `category_id`, `title`, `display`, `img`, `description`) VALUES
(8, 12, 'DANH MỤC FREE FIRE VIP', 'SHOW', 'dm_ff.png', '<p>Giá account trên 1tr&nbsp;</p>'),
(9, 12, 'DANH MỤC FREE FIRE THƯỜNG', 'SHOW', 'dm_ff.png', '<p>Giá account trên 500k</p>');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `group_caythue`
--

CREATE TABLE `group_caythue` (
  `id` int(11) NOT NULL,
  `category_caythue_id` int(11) NOT NULL,
  `title` varchar(255) NOT NULL,
  `display` varchar(25) NOT NULL,
  `money` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `group_caythue`
--

INSERT INTO `group_caythue` (`id`, `category_caythue_id`, `title`, `display`, `money`) VALUES
(6, 9, 'RANK KIM CƯƠNG -> CAO THỦ', 'SHOW', 50000);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `options`
--

CREATE TABLE `options` (
  `id` int(11) NOT NULL,
  `option_key` varchar(255) DEFAULT NULL,
  `option_value` longtext DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `options`
--

INSERT INTO `options` (`id`, `option_key`, `option_value`) VALUES
(1, 'tenweb', 'SHOP ACC GAME VERSION 1.0.0'),
(2, 'mota', 'Hệ thống bán tài khoản game tự động'),
(3, 'tukhoa', 'shop acc, code shop nick, dev1m, code ban nick game'),
(4, 'hotline', '0394278865'),
(5, 'facebook', 'https://www.facebook.com/teeth.17'),
(6, 'id_video_youtube', 't5tBVKXMh5k'),
(7, 'email_admin', 'phamvandoan17.11.2003@gmail.com'),
(8, 'email', 'phamvandoan17.11.2003@gmail.com'),
(9, 'pass_email', 'tspmunwefelaptsh'),
(10, 'thongbao', '<strong>WEBSITE KINH DOANH SHOP GAME BẰNG CÔNG NGHỆ SPRING BOOT</strong>\n\n'),
(11, 'text_left_footer', ' <span class=\"text-center\"> HỆ THỐNG BÁN ACC TỰ ĐỘNG<br>\nĐẢM BẢO UY TÍN VÀ CHẤT LƯỢNG.</span></span>\n                                                                                                                                                                                                                                                                                    '),
(12, 'text_center_footer', 'CHÚNG TÔI LUÔN LẤY UY TÍN LÀM HÀNG ĐẦU ĐỐI VỚI KHÁCH HÀNG.\nHI VỌNG SẼ ĐƯỢC PHỤC VỤ CÁC BẠN. CẢM ƠN!                                                                                                                                                                                                                                                                            '),
(13, 'html_footer', '  <div class=\"snowflakes\" aria-hidden=\"true\">\n    <div class=\"snowflake\">❅</div>\n    <div class=\"snowflake\">❆</div>\n    <div class=\"snowflake\">❅</div>\n    <div class=\"snowflake\">❆</div>\n    <div class=\"snowflake\">❅</div>\n    <div class=\"snowflake\">❆</div>\n    <div class=\"snowflake\">❅</div>\n    <div class=\"snowflake\">❆</div>\n    <div class=\"snowflake\">❅</div>\n    <div class=\"snowflake\">❆</div>\n    <div class=\"snowflake\">❅</div>\n    <div class=\"snowflake\">❆</div>\n</div>\n\n<style>\n@-webkit-keyframes snowflakes-fall {\n    0% {\n        top: -10%\n    }\n\n    100% {\n        top: 100%\n    }\n}\n\n@-webkit-keyframes snowflakes-shake {\n\n    0%,\n    100% {\n        -webkit-transform: translateX(0);\n        transform: translateX(0)\n    }\n\n    50% {\n        -webkit-transform: translateX(80px);\n        transform: translateX(80px)\n    }\n}\n\n@keyframes snowflakes-fall {\n    0% {\n        top: -10%\n    }\n\n    100% {\n        top: 100%\n    }\n}\n\n@keyframes snowflakes-shake {\n\n    0%,\n    100% {\n        transform: translateX(0)\n    }\n\n    50% {\n        transform: translateX(80px)\n    }\n}\n\n.snowflake {\n    color: #fff;\n    font-size: 1em;\n    font-family: Arial, sans-serif;\n    text-shadow: 0 0 5px #000;\n    position: fixed;\n    top: -10%;\n    z-index: 9999;\n    -webkit-user-select: none;\n    -moz-user-select: none;\n    -ms-user-select: none;\n    user-select: none;\n    cursor: default;\n    -webkit-animation-name: snowflakes-fall, snowflakes-shake;\n    -webkit-animation-duration: 10s, 3s;\n    -webkit-animation-timing-function: linear, ease-in-out;\n    -webkit-animation-iteration-count: infinite, infinite;\n    -webkit-animation-play-state: running, running;\n    animation-name: snowflakes-fall, snowflakes-shake;\n    animation-duration: 10s, 3s;\n    animation-timing-function: linear, ease-in-out;\n    animation-iteration-count: infinite, infinite;\n    animation-play-state: running, running;\n}\n\n.snowflake:nth-of-type(0) {\n    left: 1%;\n    -webkit-animation-delay: 0s, 0s;\n    animation-delay: 0s, 0s\n}\n\n.snowflake:nth-of-type(1) {\n    left: 10%;\n    -webkit-animation-delay: 1s, 1s;\n    animation-delay: 1s, 1s\n}\n\n.snowflake:nth-of-type(2) {\n    left: 20%;\n    -webkit-animation-delay: 6s, .5s;\n    animation-delay: 6s, .5s\n}\n\n.snowflake:nth-of-type(3) {\n    left: 30%;\n    -webkit-animation-delay: 4s, 2s;\n    animation-delay: 4s, 2s\n}\n\n.snowflake:nth-of-type(4) {\n    left: 40%;\n    -webkit-animation-delay: 2s, 2s;\n    animation-delay: 2s, 2s\n}\n\n.snowflake:nth-of-type(5) {\n    left: 50%;\n    -webkit-animation-delay: 8s, 3s;\n    animation-delay: 8s, 3s\n}\n\n.snowflake:nth-of-type(6) {\n    left: 60%;\n    -webkit-animation-delay: 6s, 2s;\n    animation-delay: 6s, 2s\n}\n\n.snowflake:nth-of-type(7) {\n    left: 70%;\n    -webkit-animation-delay: 2.5s, 1s;\n    animation-delay: 2.5s, 1s\n}\n\n.snowflake:nth-of-type(8) {\n    left: 80%;\n    -webkit-animation-delay: 1s, 0s;\n    animation-delay: 1s, 0s\n}\n\n.snowflake:nth-of-type(9) {\n    left: 90%;\n    -webkit-animation-delay: 3s, 1.5s;\n    animation-delay: 3s, 1.5s\n}\n\n.snowflake:nth-of-type(10) {\n    left: 25%;\n    -webkit-animation-delay: 2s, 0s;\n    animation-delay: 2s, 0s\n}\n\n.snowflake:nth-of-type(11) {\n    left: 65%;\n    -webkit-animation-delay: 4s, 2.5s;\n    animation-delay: 4s, 2.5s\n}\n</style>                                                                                                                        '),
(14, 'partner_key', '5d38086a3d2fee3901f32214ada24db0'),
(15, 'partner_id', '4712339687');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `order_caythue`
--

CREATE TABLE `order_caythue` (
  `id` int(11) NOT NULL,
  `group_caythue_id` int(11) NOT NULL,
  `money` int(11) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `status` varchar(255) DEFAULT NULL,
  `note_user` text DEFAULT NULL,
  `note_admin` text DEFAULT NULL,
  `user_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `order_caythue`
--

INSERT INTO `order_caythue` (`id`, `group_caythue_id`, `money`, `username`, `password`, `status`, `note_user`, `note_admin`, `user_id`) VALUES
(7, 6, 50000, 'username', 'password', 'hoantat', 'SV1', 'Đã hoàn thành', 7),
(8, 6, 50000, 'taikhoan1', 'matkhau1', 'hoantat', 'Sever 1', 'Đã hoàn thành', 7),
(9, 6, 50000, 'username', 'password', 'hoantat', '12323143', 'Đã hoàn thành', 7),
(10, 6, 50000, '123', '345', 'hoantat', 'hello', 'oki', 7);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `money` int(11) NOT NULL,
  `level` varchar(50) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `banned` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `users`
--

INSERT INTO `users` (`id`, `username`, `password`, `money`, `level`, `email`, `banned`) VALUES
(7, 'dev1m', '$2a$10$3M/Wx8sWd5cozN7A14vzpelkFgb4jJFRY8TrHVlPDi055NVRAHHNG', 625000, 'admin', 'phamvandoan17.11.2003@gmail.com', 0),
(9, 'titz2k3', '$2a$10$TCtQZTenlwovVrILZvDjp.oDqCrNIbnHKR9FMyW3772wAVNQs6/5S', 20000, NULL, 'ztitz2k3@gmail.com', 0),
(10, 'dev1m2', '$2a$10$RK2nXz5txMiEWMBrrK7ElurYvu9i.S4hJGcwUIwMEMKnFnmH2K6Zu', 0, 'user', '123@email.com', 0),
(11, 'tester1', '$2a$10$lCnn8Z9klwGQKe./5aFCtuoIVco/bV5AFxnffMnf3N9Pad/jvKE5O', 950000, NULL, 'admin@gmail.com', 0),
(12, 'titz1999', '$2a$10$TRyUSwOgBAWkCcOd6qmNy.mBwTeZam5k1hy5lWoOCFTFkJFsirq7C', 0, NULL, 'acccongdong2k3@gmail.com', 0),
(13, 'tester002', '$2a$10$rNx3lMqu/adA/kNMvmifXOcih8djJiPnlf8rPZMkJ2ob5xz7wlz8.', 0, NULL, 'acc@gmail.com', 0);

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `accounts`
--
ALTER TABLE `accounts`
  ADD PRIMARY KEY (`id`),
  ADD KEY `group_id` (`group_id`),
  ADD KEY `user_id` (`user_id`);

--
-- Chỉ mục cho bảng `banks`
--
ALTER TABLE `banks`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `bank_auto`
--
ALTER TABLE `bank_auto`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_id` (`user_id`);

--
-- Chỉ mục cho bảng `cards`
--
ALTER TABLE `cards`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_id` (`user_id`);

--
-- Chỉ mục cho bảng `cash_flow`
--
ALTER TABLE `cash_flow`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_id` (`user_id`);

--
-- Chỉ mục cho bảng `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `category_caythue`
--
ALTER TABLE `category_caythue`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `groups`
--
ALTER TABLE `groups`
  ADD PRIMARY KEY (`id`),
  ADD KEY `category_id` (`category_id`);

--
-- Chỉ mục cho bảng `group_caythue`
--
ALTER TABLE `group_caythue`
  ADD PRIMARY KEY (`id`),
  ADD KEY `category_caythue_id` (`category_caythue_id`);

--
-- Chỉ mục cho bảng `options`
--
ALTER TABLE `options`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `order_caythue`
--
ALTER TABLE `order_caythue`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_id` (`user_id`),
  ADD KEY `group_caythue_id` (`group_caythue_id`);

--
-- Chỉ mục cho bảng `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `accounts`
--
ALTER TABLE `accounts`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=39;

--
-- AUTO_INCREMENT cho bảng `banks`
--
ALTER TABLE `banks`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT cho bảng `bank_auto`
--
ALTER TABLE `bank_auto`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `cards`
--
ALTER TABLE `cards`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `cash_flow`
--
ALTER TABLE `cash_flow`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=32;

--
-- AUTO_INCREMENT cho bảng `category`
--
ALTER TABLE `category`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT cho bảng `category_caythue`
--
ALTER TABLE `category_caythue`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT cho bảng `groups`
--
ALTER TABLE `groups`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT cho bảng `group_caythue`
--
ALTER TABLE `group_caythue`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT cho bảng `options`
--
ALTER TABLE `options`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT cho bảng `order_caythue`
--
ALTER TABLE `order_caythue`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT cho bảng `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `accounts`
--
ALTER TABLE `accounts`
  ADD CONSTRAINT `accounts_ibfk_1` FOREIGN KEY (`group_id`) REFERENCES `groups` (`id`),
  ADD CONSTRAINT `accounts_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

--
-- Các ràng buộc cho bảng `bank_auto`
--
ALTER TABLE `bank_auto`
  ADD CONSTRAINT `bank_auto_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

--
-- Các ràng buộc cho bảng `cards`
--
ALTER TABLE `cards`
  ADD CONSTRAINT `cards_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

--
-- Các ràng buộc cho bảng `cash_flow`
--
ALTER TABLE `cash_flow`
  ADD CONSTRAINT `cash_flow_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

--
-- Các ràng buộc cho bảng `groups`
--
ALTER TABLE `groups`
  ADD CONSTRAINT `groups_ibfk_1` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`);

--
-- Các ràng buộc cho bảng `group_caythue`
--
ALTER TABLE `group_caythue`
  ADD CONSTRAINT `group_caythue_ibfk_1` FOREIGN KEY (`category_caythue_id`) REFERENCES `category_caythue` (`id`);

--
-- Các ràng buộc cho bảng `order_caythue`
--
ALTER TABLE `order_caythue`
  ADD CONSTRAINT `order_caythue_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `order_caythue_ibfk_2` FOREIGN KEY (`group_caythue_id`) REFERENCES `group_caythue` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
