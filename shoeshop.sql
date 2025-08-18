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
    Gender NVARCHAR(10),
    CreatedAt DATETIME DEFAULT GETDATE()
);

CREATE TABLE ProductDetails (
    Id INT IDENTITY PRIMARY KEY,
    ProductId INT FOREIGN KEY REFERENCES Products(Id),
    ColorId INT FOREIGN KEY REFERENCES Colors(Id),
    SizeId INT FOREIGN KEY REFERENCES Size(Id),
    Stock INT NOT NULL,
	Price Decimal(18,3) Null
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
    CreatedAt DATETIME DEFAULT GETDATE(),
	BuyerEmail NVARCHAR(50),
    BuyerName NVARCHAR(50),
    BuyerPhoneNumber NVARCHAR(50),
    ShippingFee DECIMAL(18,2),
    PaymentType Int,
    AmountPaid DECIMAL (18,2),
    AddressID INT FOREIGN KEY REFERENCES Address(ID),
    VoucherID INT FOREIGN KEY REFERENCES Voucher(ID)
);

CREATE TABLE OrderDetails (
    Id INT IDENTITY PRIMARY KEY,
    OrderId INT FOREIGN KEY REFERENCES Orders(Id),
    ProductDetailId INT FOREIGN KEY REFERENCES ProductDetails(Id),
    Quantity INT NOT NULL,
    Price DECIMAL(18,2) NOT NULL,
	PriceAtPurchase DECIMAL(18,2)
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

 CREATE TABLE Address (
    ID int identity (1,1) primary key,
    AddressDetail NVARCHAR(255),
    CommuneLabel  NVARCHAR(255),
    CommuneValue Int,
    DistrictLabel  NVARCHAR(255),
    DistrictValue Int,
    ProvinceLabel  NVARCHAR(255),
    ProvinceValue Int
)

CREATE TABLE Voucher(
    ID int identity (1,1) primary key,
    Code  NVARCHAR(50),
    CreatedAt DATETIME,
    StartAt DATETIME,
    EndDate DATETIME,
    UpdateDate DATETIME,
    Name  NVARCHAR(255),
    Quantity int,
    MaxValue DECIMAL(18,2),
    Type int,
    Value DECIMAL(18,2)
)
--This table is used to store the list of emails registered to receive promotional information and special offers from the system
CREATE TABLE Emails (
    ID INT identity(1,1) PRIMARY KEY,
    UserID INT NULL, -- Cho phép null để hỗ trợ khách vãng lai
    Email NVARCHAR(100) NOT NULL,
    IsActive BIT DEFAULT 1,
    CreatedAt DATETIME DEFAULT GETDATE(),
    FOREIGN KEY (UserID) REFERENCES Users(ID)
);

