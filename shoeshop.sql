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
    BrandName NVARCHAR(100) UNIQUE NOT NULL,
	Logo NVARCHAR(MAX)
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
CREATE TABLE ProductDetails (
    Id INT IDENTITY PRIMARY KEY,
    ProductId INT FOREIGN KEY REFERENCES Products(Id),
    ColorId INT FOREIGN KEY REFERENCES Colors(Id),
    SizeId INT FOREIGN KEY REFERENCES Size(Id),
    Stock INT NOT NULL,
);
CREATE TABLE Colors (
    ID INT IDENTITY(1,1) PRIMARY KEY,
    ColorName NVARCHAR(50) UNIQUE NOT NULL
);

CREATE TABLE ProductImages (
    ID INT IDENTITY(1,1) PRIMARY KEY,
    ProductID INT FOREIGN KEY REFERENCES Products(ID) ,
    ImageURL NVARCHAR(255) NOT NULL
	IsThumbnail BIT DEFAULT 0
);
CREATE TABLE Carts (
    ID INT IDENTITY(1,1) PRIMARY KEY,
    UserID INT FOREIGN KEY REFERENCES Users(ID),
    CreatedAt DATETIME DEFAULT GETDATE()
);

CREATE TABLE CartItems (
    Id INT IDENTITY PRIMARY KEY,
    CartId INT FOREIGN KEY REFERENCES Carts(Id),
    ProductDetailId INT FOREIGN KEY REFERENCES ProductDetails(Id),
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
    Id INT IDENTITY PRIMARY KEY,
    OrderId INT FOREIGN KEY REFERENCES Orders(Id),
    ProductDetailId INT FOREIGN KEY REFERENCES ProductDetails(Id),
    Quantity INT NOT NULL,
    Price DECIMAL(18,2) NOT NULL
);

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

CREATE TABLE Size (
    ID INT IDENTITY PRIMARY KEY,
    SizeValue FLOAT NOT NULL
);

CREATE TABLE Category (
    ID INT IDENTITY PRIMARY KEY,
    Name NVARCHAR(255) NOT NULL
);

CREATE TABLE ProductCategory(
ProductID INT FOREIGN KEY REFERENCES Products(ID) ,
CategoryID INT FOREIGN KEY REFERENCES Category(ID) ,
  PRIMARY KEY (ProductID, CategoryID)
 )
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


insert into Size(SizeValue) values (37)
insert into Size(SizeValue) values (35)
insert into Size(SizeValue) values (34)
insert into Size(SizeValue) values (33)

INSERT INTO ProductDetails (ProductId, ColorId, SizeId, Stock, SKU)
VALUES (1, 1, 1, 10);

-- Red, Size 38, hết hàng
INSERT INTO ProductDetails (ProductId, ColorId, SizeId, Stock, SKU)
VALUES (1, 1, 2, 0);

-- Blue, Size 38, còn hàng
INSERT INTO ProductDetails (ProductId, ColorId, SizeId, Stock, SKU)
VALUES (1, 2, 2, 5);

-- Blue, Size 39, còn hàng
INSERT INTO ProductDetails (ProductId, ColorId, SizeId, Stock, SKU)
VALUES (1, 2, 3, 8);
INSERT INTO Category (Name)
VALUES 
(N'Giày thể thao'),
(N'Giày đá bóng'),
(N'Giày chạy')


-- Gán danh mục cho các sản phẩm
INSERT INTO ProductCategory (ProductID, CategoryID)
VALUES 
(1, 1),
(2, 2),
(3, 1), 
(3, 3), 
(4, 3),
(5, 3),
(6, 1);

Select * from Products p join ProductColors pc on p.ID = pc.ProductID join Colors c on c.id= pc.ColorID
SELECT top 5
	p.ID,
    p.ProductName,
    p.BrandID,
    p.Price,
	pimg.ImageURL,
	pc.CategoryID
FROM Products p
LEFT JOIN ProductImages pimg ON p.ID = pimg.ProductID AND pimg.IsThumbnail=1
LEFT JOIN ProductCategory pc on pc.ProductID = p.ID
WHERE pc.CategoryID=1
order by p.CreatedAt desc;

select *from Carts 
select p.ProductName,p.Price,ci.Quantity from CartItems ci
LEFT JOIN ProductDetails pd on pd.Id = ci.Id
LEFT JOIN Products p on pd.ProductId = p.ID
LEFT JOIN Carts c on c.ID = ci.CartId
LEFT JOIN Users u on u.Id = c.UserID
WHERE u.id = 11
sl
SELECT * FROM ProductDetails WHERE Id = 6;

SELECT 
    p.ProductName, 
    p.Price, 
	 p.BrandID,
    ci.Quantity,
    col.ColorName,
    s.SizeValue,
	pimg.ImageURL
FROM CartItems ci
LEFT JOIN ProductDetails pd ON pd.Id = ci.ProductDetailId 
LEFT JOIN Products p ON pd.ProductId = p.ID
LEFT JOIN Colors col ON pd.ColorId = col.ID
LEFT JOIN Size s ON pd.SizeId = s.ID
LEFT JOIN Carts c ON c.ID = ci.CartId
LEFT JOIN Users u ON u.Id = c.UserID
LEFT JOIN ProductImages pimg ON p.ID = pimg.ProductID AND pimg.IsThumbnail=1
WHERE u.id = 11;
