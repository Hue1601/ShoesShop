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
    IsGoogleLogin BIT DEFAULT 0, -- Đăng nhập bằng Google
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
    Price DECIMAL(18,2) NOT NULL,
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
    OrderDetailID INT IDENTITY(1,1) PRIMARY KEY,
    OrderID INT FOREIGN KEY REFERENCES Orders(ID) ,
    ProductID INT FOREIGN KEY REFERENCES Products(ID),
    Quantity INT NOT NULL,
    Price DECIMAL(18,2) NOT NULL
);

CREATE TABLE Collections (
    ID INT IDENTITY(1,1) PRIMARY KEY,
    CollectionName NVARCHAR(100) UNIQUE NOT NULL
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

