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

drop table CartItems
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
drop table OrderDetails

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

CREATE TABLE ProductDetails (
    Id INT IDENTITY PRIMARY KEY,
    ProductId INT FOREIGN KEY REFERENCES Products(Id),
    ColorId INT FOREIGN KEY REFERENCES Colors(Id),
    SizeId INT FOREIGN KEY REFERENCES Size(Id),
    Stock INT NOT NULL,
    SKU NVARCHAR(100) UNIQUE
);

CREATE TABLE ProductSize (
  ProductID INT FOREIGN KEY REFERENCES Products(ID),
  SizeID INT FOREIGN KEY REFERENCES Size(ID),
   PRIMARY KEY (ProductID, SizeID)
)

CREATE TABLE Size (
    ID INT IDENTITY PRIMARY KEY,
    SizeValue FLOAT NOT NULL
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


insert into Size(SizeValue) values (37)
insert into Size(SizeValue) values (35)
insert into Size(SizeValue) values (34)
insert into Size(SizeValue) values (33)

INSERT INTO ProductDetails (ProductId, ColorId, SizeId, Stock, SKU)
VALUES (1, 1, 1, 10, 'NIKE-AIRMAX-RED-37');

-- Red, Size 38, hết hàng
INSERT INTO ProductDetails (ProductId, ColorId, SizeId, Stock, SKU)
VALUES (1, 1, 2, 0, 'NIKE-AIRMAX-RED-38');

-- Blue, Size 38, còn hàng
INSERT INTO ProductDetails (ProductId, ColorId, SizeId, Stock, SKU)
VALUES (1, 2, 2, 5, 'NIKE-AIRMAX-BLUE-38');

-- Blue, Size 39, còn hàng
INSERT INTO ProductDetails (ProductId, ColorId, SizeId, Stock, SKU)
VALUES (1, 2, 3, 8, 'NIKE-AIRMAX-BLUE-39');

Select * from Products p join ProductColors pc on p.ID = pc.ProductID join Colors c on c.id= pc.ColorID
SELECT top 5
	p.ID,
    p.ProductName,
    p.BrandID,
    p.Price,
	pimg.ImageURL,
	c.ColorName
FROM Products p
LEFT JOIN ProductImages pimg ON p.ID = pimg.ProductID
LEFT JOIN ProductColors pc on pc.ProductID = p.ID
LEFT JOIN Colors c on c.ID = pc.ColorID
order by p.CreatedAt desc;

select c.ColorName  from ProductColors pc
JOIN Products p on p.ID = pc.ProductID
JOIN Colors c on c.ID = pc.ColorID
where p.ID = '1'

select * from Colors c
join ProductColors pc on pc.ColorID = c.ID
join Products p on p.ID = pc.ProductID
where p.ID = 1



select p.id, p.ProductName,p.Description,p.Price, img.ImageURL
from Products p
LEFT JOIN Brands b on b.ID = p.BrandID
LEFT JOIN ProductImages img on p.ID = img.ID
where p.id=1
select * from ProductImages
select * from Colors
select * from Size
select * from ProductColor

select * from ProductDetails

select p.ProductName,b.BrandName,p.Price,p.Description,c.ColorName,s.SizeValue
from ProductDetails pd
LEFT JOIN Products p on p.ID = pd.ProductId
LEFT JOIN Colors c on c.ID = pd.ColorId
LEFT JOIN Size s on s.ID = pd.SizeId
LEFT JOIN Brands b on b.ID = p.BrandID
Where p.ID = 1

SELECT 
    p.ID AS ProductID,
    p.ProductName,
    p.Description,
    p.Price,

    b.BrandName,

    pi.ImageURL,

    c.ColorName,
    s.SizeValue,

    pd.Stock,
    pd.SKU,

    d.DiscountPercentage

FROM Products p

-- JOIN Brand
LEFT JOIN Brands b ON p.BrandID = b.ID

-- JOIN ảnh sản phẩm
LEFT JOIN ProductImages pi ON p.ID = pi.ProductID

-- JOIN chi tiết sản phẩm
LEFT JOIN ProductDetails pd ON p.ID = pd.ProductId

-- JOIN màu sắc
LEFT JOIN Colors c ON pd.ColorId = c.ID

-- JOIN kích thước
LEFT JOIN Size s ON pd.SizeId = s.ID

-- JOIN giảm giá (nếu có)
LEFT JOIN ProductDiscounts pdsc ON pdsc.ProductID = p.ID
LEFT JOIN Discounts d ON pdsc.DiscountID = d.ID

WHERE 
    p.ID = 1 -- <-- truyền ID sản phẩm cụ thể ở đây
