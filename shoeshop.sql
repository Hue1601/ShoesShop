Create database shoeshop
go 
use shoeshop
go

CREATE TABLE Users (
    Id INT IDENTITY(1,1) PRIMARY KEY,
    Username NVARCHAR(50) UNIQUE NOT NULL,
    Password NVARCHAR(255) NOT NULL,
    PhoneNumber NVARCHAR(15) UNIQUE NOT NULL,
    Email NVARCHAR(100) UNIQUE NOT NULL,
    GoogleLogin BIT DEFAULT 0, -- Đăng nhập bằng Google
    CreatedAt DATETIME DEFAULT GETDATE()
);
CREATE TABLE Brands (
    ID INT IDENTITY(1,1) PRIMARY KEY,
    BrandName NVARCHAR(100) UNIQUE NOT NULL
);

CREATE TABLE Products (
    ID INT IDENTITY(1,1) PRIMARY KEY,
    ProductName NVARCHAR(255) NOT NULL,
    BrandID INT FOREIGN KEY REFERENCES Brands(ID) ,
    Description NVARCHAR(MAX),
    Price DECIMAL(18,3) NOT NULL,
    Gender NVARCHAR(10),
    CreatedAt DATETIME DEFAULT GETDATE()
);

CREATE TABLE Colors (
    ID INT IDENTITY(1,1) PRIMARY KEY,
    ColorName NVARCHAR(50) UNIQUE NOT NULL
);
CREATE TABLE ProductColors (
    ProductID INT FOREIGN KEY REFERENCES Products(ID),
    ColorID INT FOREIGN KEY REFERENCES Colors(ID),
    PRIMARY KEY (ProductID, ColorID)
);

CREATE TABLE ProductImages (
    ID INT IDENTITY(1,1) PRIMARY KEY,
    ProductID INT FOREIGN KEY REFERENCES Products(ID) ,
    ImageURL NVARCHAR(255) NOT NULL
);
CREATE TABLE Carts (
    ID INT IDENTITY(1,1) PRIMARY KEY,
    UserID INT FOREIGN KEY REFERENCES Users(ID),
    CreatedAt DATETIME DEFAULT GETDATE()
);
CREATE TABLE CartItems (
    ID INT IDENTITY(1,1) PRIMARY KEY,
    CartID INT FOREIGN KEY REFERENCES Carts(ID),
    ProductID INT FOREIGN KEY REFERENCES Products(ID),
    Quantity INT NOT NULL 
);
CREATE TABLE Orders (
    ID INT IDENTITY(1,1) PRIMARY KEY,
    UserID INT FOREIGN KEY REFERENCES Users(ID),
    TotalAmount DECIMAL(18,2) NOT NULL,
    OrderStatus NVARCHAR(50) ,
    CreatedAt DATETIME DEFAULT GETDATE()
);
CREATE TABLE OrderDetails (
	ID INT IDENTITY(1,1) PRIMARY KEY,
    OrderID INT FOREIGN KEY REFERENCES Orders(ID) ,
    ProductID INT FOREIGN KEY REFERENCES Products(ID),
    Quantity INT NOT NULL,
    Price DECIMAL(18,2) NOT NULL
);
drop table Collections
CREATE TABLE Collections (
    ID INT IDENTITY(1,1) PRIMARY KEY,
    CollectionName NVARCHAR(100) UNIQUE NOT NULL,
	CreatedAt DATETIME DEFAULT GETDATE()
);
CREATE TABLE ProductCollections (
    ProductID INT FOREIGN KEY REFERENCES Products(ID),
    CollectionID INT FOREIGN KEY REFERENCES Collections(ID) ,
    PRIMARY KEY (ProductID, CollectionID)
);
CREATE TABLE Discounts (
    ID INT IDENTITY(1,1) PRIMARY KEY,
    DiscountPercentage FLOAT ,
	CreateAt DATETIME,
    StartDate DATETIME NOT NULL,
    EndDate DATETIME NOT NULL
);
CREATE TABLE ProductDiscounts (
    ProductID INT FOREIGN KEY REFERENCES Products(ID) ,
    DiscountID INT FOREIGN KEY REFERENCES Discounts(ID) ,
    PRIMARY KEY (ProductID, DiscountID)
);
-- Thêm dữ liệu vào bảng Brands
INSERT INTO Brands (BrandName) VALUES 
('Nike'), 
('Adidas'), 
('Puma'), 
('Reebok'), 
('New Balance');

-- Thêm dữ liệu vào bảng Colors
INSERT INTO Colors (ColorName) VALUES 
('Red'), 
('Blue'), 
('Black'), 
('White'), 
('Green');

-- Thêm dữ liệu vào bảng Collections
INSERT INTO Collections (CollectionName) VALUES 
('Summer 2024'), 
('Winter 2024'), 
('Limited Edition'), 
('Sportswear'), 
('Casual');

-- Thêm dữ liệu vào bảng Discounts
INSERT INTO Discounts (DiscountPercentage, CreateAt, StartDate, EndDate) VALUES 
(10, GETDATE(), '2024-06-01', '2024-06-30'), 
(15, GETDATE(), '2024-07-01', '2024-07-31'),
(20, GETDATE(), '2024-08-01', '2024-08-31');

-- Thêm dữ liệu vào bảng Products
INSERT INTO Products (ProductName, BrandID, Description, Price, Gender, CreatedAt) VALUES 
('Nike Air Max', 1, 'Comfortable running shoes', 120.99, 'Unisex', GETDATE()), 
('Adidas Ultraboost', 2, 'High-performance running shoes', 140.50, 'Male', GETDATE()), 
('Puma Classic', 3, 'Casual sneakers', 85.00, 'Female', GETDATE()), 
('Reebok Nano', 4, 'Cross-training shoes', 110.00, 'Male', GETDATE()), 
('New Balance 574', 5, 'Classic retro sneakers', 95.99, 'Unisex', GETDATE());

-- Thêm dữ liệu vào bảng ProductColors
INSERT INTO ProductColors (ProductID, ColorID) VALUES 
(1, 1), (1, 2), (1, 3), -- Nike Air Max có Red, Blue, Black
(2, 3), (2, 4),         -- Adidas Ultraboost có Black, White
(3, 2), (3, 5),         -- Puma Classic có Blue, Green
(4, 3), (4, 4), (4, 5), -- Reebok Nano có Black, White, Green
(5, 1), (5, 2),        -- New Balance 574 có Red, Blue
(6, 1), (6, 2), (6, 3), 
(7, 3), (8, 4),        
(8, 2), (7, 5),         
(9, 3), (9, 4), (9, 5), 
(10, 1), (10, 2);        

-- Thêm dữ liệu vào bảng ProductCollections
INSERT INTO ProductCollections (ProductID, CollectionID) VALUES 
(1, 1), (2, 1), -- Nike Air Max & Adidas Ultraboost trong Summer 2024
(3, 2), (4, 2), -- Puma Classic & Reebok Nano trong Winter 2024
(5, 3), (1, 3), -- New Balance 574 & Nike Air Max trong Limited Edition
(2, 4), (3, 5), -- Adidas Ultraboost trong Sportswear & Puma Classic trong Casual
(5, 5);         -- New Balance 574 trong Casual

-- Thêm dữ liệu vào bảng ProductDiscounts
INSERT INTO ProductDiscounts (ProductID, DiscountID) VALUES 
(1, 1), (2, 2), -- Nike Air Max được giảm 10%, Adidas Ultraboost giảm 15%
(3, 3), (4, 1), -- Puma Classic giảm 20%, Reebok Nano giảm 10%
(5, 2);         -- New Balance 574 giảm 15%

-- Thêm dữ liệu vào bảng ProductImages
INSERT INTO ProductImages (ProductID, ImageURL) VALUES 
(1, '/uploads/images/nike-air-max-1.jpg') ,
(2, '/uploads/images/adidas-ultraboost-1.jpg');


drop table ProductImages
ALTER TABLE Products 
ALTER COLUMN Price DECIMAL(18,3) NOT NULL;

Select * from Products p join ProductColors pc on p.ID = pc.ProductID join Colors c on c.id= pc.ColorID
SELECT top 5
    p.ID AS ProductID,
    p.ProductName,
    p.Description,
    p.Price,
    p.Gender,
    p.CreatedAt,
    c.ColorName
FROM Products p
LEFT JOIN ProductColors pc ON p.ID = pc.ProductID
LEFT JOIN Colors c ON pc.ColorID = c.ID
order by p.CreatedAt desc;

SELECT TOP 5 
    p.ID AS ProductID,
    p.ProductName,
    p.Price,
    c.CollectionName,
FROM Products p
JOIN ProductCollections pc ON p.ID = pc.ProductID
JOIN Collections c ON pc.CollectionID = c.ID
WHERE c.ID = (SELECT TOP 1 ID FROM Collections ORDER BY CreatedAt DESC) -- Lấy ID của bộ sưu tập mới nhất
ORDER BY p.CreatedAt DESC; -- Sắp xếp sản phẩm theo thời gian mới nhất


