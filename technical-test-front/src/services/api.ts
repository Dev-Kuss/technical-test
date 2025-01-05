import axios from 'axios';
import { Product } from '../types/Product';

const API_URL = import.meta.env.VITE_API_URL || '/api';
const PRICE_API_URL = import.meta.env.VITE_PRICE_API_URL || '/api/prices';

const productApi = axios.create({
    baseURL: API_URL
});

const priceApi = axios.create({
    baseURL: PRICE_API_URL
});

export const ProductService = {
    getAllProducts: async () => {
        const response = await productApi.get<Product[]>('/products');
        return response.data;
    },

    getProduct: async (id: number) => {
        const response = await productApi.get<Product>(`/products/${id}`);
        return response.data;
    },

    createProduct: async (product: Omit<Product, 'id'>) => {
        const response = await productApi.post<Product>('/products', product);
        return response.data;
    },

    updateProduct: async (id: number, product: Product) => {
        const response = await productApi.put<Product>(`/products/${id}`, product);
        return response.data;
    },

    deleteProduct: async (id: number) => {
        await productApi.delete(`/products/${id}`);
    },

    getMostExpensiveProduct: async () => {
        const response = await priceApi.get<Product>('/most-expensive');
        return response.data;
    },

    getAveragePrice: async () => {
        const response = await priceApi.get<string>('/average');
        return parseFloat(response.data);
    }
};
