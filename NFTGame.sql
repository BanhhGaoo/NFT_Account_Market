CREATE DATABASE NFTGameAccountMarketplace;
GO

-- Sử dụng cơ sở dữ liệu vừa tạo
USE NFTGameAccountMarketplace;
GO

-- Tạo bảng User
CREATE TABLE Users (
    userId INT PRIMARY KEY IDENTITY(1,1),
    username NVARCHAR(50) NOT NULL,
    email NVARCHAR(100) NOT NULL,
    password NVARCHAR(255) NOT NULL,
    publicKey NVARCHAR(255),
    twoFactorEnabled BIT NOT NULL DEFAULT 0
);
GO

-- Tạo bảng GameAccount
CREATE TABLE GameAccounts (
    accountId INT PRIMARY KEY IDENTITY(1,1),
    accountName NVARCHAR(50) NOT NULL,
    gameName NVARCHAR(50) NOT NULL,
    description NVARCHAR(255),
    price FLOAT NOT NULL,
	Selled BIT NOT NULL DEFAULT 0
);
GO

-- Img account
CREATE TABLE Images (
    imageId INT PRIMARY KEY IDENTITY(1,1),
    accountId INT NOT NULL,
    imageUrl NVARCHAR(255) NOT NULL,
    FOREIGN KEY (accountId) REFERENCES GameAccounts(accountId)
);
GO

-- Tạo bảng CartItem
CREATE TABLE CartItems (
    cartItemId INT PRIMARY KEY IDENTITY(1,1),
    userId INT NOT NULL,
    accountId INT NOT NULL,
    FOREIGN KEY (userId) REFERENCES Users(userId),
    FOREIGN KEY (accountId) REFERENCES GameAccounts(accountId)
);
GO

-- Tạo bảng Orders
CREATE TABLE Orders (
    orderId INT PRIMARY KEY IDENTITY(1,1),
    userId INT NOT NULL,
    orderDate DATETIME NOT NULL DEFAULT GETDATE(),
    totalAmount float NOT NULL,
    FOREIGN KEY (userId) REFERENCES Users(userId)
);
GO


ALTER TABLE Orders
ALTER COLUMN totalAmount FLOAT;

-- Tạo bảng OrderDetails
CREATE TABLE OrderDetails (
    orderDetailId INT PRIMARY KEY IDENTITY(1,1),
    orderId INT NOT NULL,
    accountId INT NOT NULL,
    price FLOAT NOT NULL,
    FOREIGN KEY (orderId) REFERENCES Orders(orderId),
    FOREIGN KEY (accountId) REFERENCES GameAccounts(accountId)
);
GO



-- Tạo bảng Payments
CREATE TABLE Payments (
    paymentId INT PRIMARY KEY IDENTITY(1,1),
    orderId INT NOT NULL,
    paymentDate DATETIME NOT NULL DEFAULT GETDATE(),
    amount DECIMAL(18, 2) NOT NULL,
    status NVARCHAR(50) NOT NULL,
    FOREIGN KEY (orderId) REFERENCES Orders(orderId)
);
GO

-- Tạo bảng Blockchain
CREATE TABLE Blockchain (
    transactionId INT PRIMARY KEY IDENTITY(1,1),
    paymentId INT NOT NULL,
    transactionHash NVARCHAR(255) NOT NULL,
    FOREIGN KEY (paymentId) REFERENCES Payments(paymentId)
);
GO

-- Tạo bảng Admins
CREATE TABLE Admins (
    adminId INT PRIMARY KEY IDENTITY(1,1),
    adminName NVARCHAR(50) NOT NULL,
    email NVARCHAR(100) NOT NULL,
    password NVARCHAR(255) NOT NULL
);
GO

-- Tạo bảng AdminPayment
CREATE TABLE AdminPayment (
    adminId INT NOT NULL,
    paymentId INT NOT NULL,
    PRIMARY KEY (adminId, paymentId),
    FOREIGN KEY (adminId) REFERENCES Admins(adminId),
    FOREIGN KEY (paymentId) REFERENCES Payments(paymentId)
);
GO

-------------------------------------------------------------------------------

-- Chèn dữ liệu vào bảng Users
INSERT INTO Users (username, email, password, publicKey, twoFactorEnabled)
VALUES 
('user1', 'user1@example.com', 'password123', 'publicKey1', 0),
('user2', 'user2@example.com', 'password456', 'publicKey2', 1),
('user3', 'user3@example.com', 'password789', 'publicKey3', 0);
GO

-- Chèn dữ liệu vào bảng GameAccounts
INSERT INTO GameAccounts (accountName, gameName, description, price)
VALUES 
('valorant_user1', 'Valorant', 'This is valorant_user1 for Valorant', 50.00),
('valorant_user2', 'Valorant', 'This is valorant_user2 for Valorant', 55.00),
('valorant_user3', 'Valorant', 'This is valorant_user3 for Valorant', 60.00),
('valorant_user4', 'Valorant', 'This is valorant_user4 for Valorant', 65.00),
('valorant_user5', 'Valorant', 'This is valorant_user5 for Valorant', 70.00),
('valorant_user6', 'Valorant', 'This is valorant_user6 for Valorant', 75.00),
('valorant_user7', 'Valorant', 'This is valorant_user7 for Valorant', 80.00),
('valorant_user8', 'Valorant', 'This is valorant_user8 for Valorant', 85.00),
('valorant_user9', 'Valorant', 'This is valorant_user9 for Valorant', 90.00),
('lol_user1', 'Lol', 'This is lol_user1 for Lol', 65.00),
('lol_user2', 'Lol', 'This is lol_user2 for Lol', 70.00),
('lol_user3', 'Lol', 'This is lol_user3 for Lol', 75.00),
('lol_user4', 'Lol', 'This is lol_user4 for Lol', 80.00),
('lol_user5', 'Lol', 'This is lol_user5 for Lol', 85.00),
('lol_user6', 'Lol', 'This is lol_user6 for Lol', 90.00),
('lol_user7', 'Lol', 'This is lol_user7 for Lol', 95.00),
('lol_user8', 'Lol', 'This is lol_user8 for Lol', 100.00),
('lol_user9', 'Lol', 'This is lol_user9 for Lol', 105.00),
('tft_user1', 'TFT', 'This is tft_user1 for TFT', 80.00),
('tft_user2', 'TFT', 'This is tft_user2 for TFT', 85.00),
('tft_user3', 'TFT', 'This is tft_user3 for TFT', 90.00),
('tft_user4', 'TFT', 'This is tft_user4 for TFT', 95.00),
('tft_user5', 'TFT', 'This is tft_user5 for TFT', 100.00),
('tft_user6', 'TFT', 'This is tft_user6 for TFT', 105.00),
('tft_user7', 'TFT', 'This is tft_user7 for TFT', 110.00),
('tft_user8', 'TFT', 'This is tft_user8 for TFT', 115.00),
('tft_user9', 'TFT', 'This is tft_user9 for TFT', 120.00);
GO

INSERT INTO Images (accountId, imageUrl)
VALUES 
    (1, 'acc1.png'),
    (1, 'acc1.1.png'),
    (1, 'acc1.2.png'),
	(2, 'acc1.png'),
    (2, 'acc1.1.png'),
    (2, 'acc1.2.png'),
	(3, 'acc1.png'),
    (3, 'acc1.1.png'),
    (3, 'acc1.2.png'),
	(4, 'acc1.png'),
    (4, 'acc1.1.png'),
    (4, 'acc1.2.png'),
	(5, 'acc1.png'),
    (5, 'acc1.1.png'),
    (5, 'acc1.2.png'),
	(6, 'acc1.png'),
    (6, 'acc1.1.png'),
    (6, 'acc1.2.png'),
	(7, 'acc1.png'),
    (7, 'acc1.1.png'),
    (7, 'acc1.2.png'),
	(8, 'acc1.png'),
    (8, 'acc1.1.png'),
    (8, 'acc1.2.png'),
	(9, 'acc1.png'),
    (9, 'acc1.1.png'),
    (9, 'acc1.2.png')
GO

-- Chèn dữ liệu vào bảng CartItems
INSERT INTO CartItems (userId, accountId)
VALUES 
(1, 1),
(1, 2),
(2, 2),
(2, 3),
(3, 1);
GO

-- Chèn dữ liệu vào bảng Orders
INSERT INTO Orders (userId, orderDate, totalAmount)
VALUES 
(1, GETDATE(), 175.00),
(2, GETDATE(), 175.00),
(3, GETDATE(), 150.00);
GO

-- Chèn dữ liệu vào bảng OrderDetails
INSERT INTO OrderDetails (orderId, accountId, price)
VALUES 
(1, 1, 50.00),
(1, 2, 75.00),
(2, 2, 75.00),
(2, 3, 100.00),
(3, 1, 50.00);
GO

-- Chèn dữ liệu vào bảng Payments
INSERT INTO Payments (orderId, paymentDate, amount, status)
VALUES 
(1, GETDATE(), 175.00, 'Completed'),
(2, GETDATE(), 175.00, 'Completed'),
(3, GETDATE(), 150.00, 'Pending');
GO

-- Chèn dữ liệu vào bảng Blockchain
INSERT INTO Blockchain (paymentId, transactionHash)
VALUES 
(1, 'txHash1'),
(2, 'txHash2'),
(3, 'txHash3');
GO

-- Chèn dữ liệu vào bảng Admins
INSERT INTO Admins (adminName, email, password)
VALUES 
('admin1', 'admin1@example.com', 'adminpassword1'),
('admin2', 'admin2@example.com', 'adminpassword2');
GO

-- Chèn dữ liệu vào bảng AdminPayment
INSERT INTO AdminPayment (adminId, paymentId)
VALUES 
(1, 1),
(1, 2),
(2, 3);
GO