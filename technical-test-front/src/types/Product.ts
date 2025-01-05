export interface BaseProduct {
    id?: number;
    name: string;
    price: number;
    onSale: boolean;
}

export interface PhysicalProduct extends BaseProduct {
    product_type: 'PHYSICAL';
    weight: number;
}

export interface DigitalProduct extends BaseProduct {
    product_type: 'DIGITAL';
    sizeMB: number;
}

export type Product = PhysicalProduct | DigitalProduct;
