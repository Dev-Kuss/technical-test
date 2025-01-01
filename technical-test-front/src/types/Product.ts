export interface BaseProduct {
    id?: number;
    name: string;
    price: number;
    onSale: boolean;
}

export interface PhysicalProduct extends BaseProduct {
    '@type': 'PhysicalProduct';
    weight: number;
}

export interface DigitalProduct extends BaseProduct {
    '@type': 'DigitalProduct';
    sizeMB: number;
}

export type Product = PhysicalProduct | DigitalProduct;
