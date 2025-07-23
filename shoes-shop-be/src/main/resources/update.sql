EXEC sp_rename 'OrderDetails.OrderDetailID', 'ID', 'COLUMN';
EXEC sp_rename 'Users.IsGoogleLogin','GoogleLogin',"COLUMN"
EXEC sp_rename 'Voucher.StartAt','StartDate',"COLUMN"
EXEC sp_rename 'Voucher.UpdateDate','UpdateAt',"COLUMN"

ALTER TABLE Collections
    ADD CreatedAt DATETIME DEFAULT GETDATE();
Alter TABLE Brands ADD Logo NVARCHAR(MAX)

Alter table ProductImages add IsThumbnail Bit DEFAULT 0

Alter table Orders ADD  BuyerEmail NVARCHAR(50),
                       BuyerName NVARCHAR(50),
                       BuyerPhoneNumber NVARCHAR(50),
                       ShippingFee DECIMAL(18,2),
                       PaymentType Int,
                       AmountPaid DECIMAL (18,2),
                       AddressID INT FOREIGN KEY REFERENCES Address(ID),
                       VoucherID INT FOREIGN KEY REFERENCES Voucher(ID)

Alter table OrderDetails ADD PriceAtPurchase DECIMAL(18,2)

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