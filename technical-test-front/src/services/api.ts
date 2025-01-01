import axios from 'axios';
import { Product } from '../types/Product';

const productApi = axios.create({
    baseURL: 'http://localhost:8080/api'
});

const priceApi = axios.create({
    baseURL: 'http://localhost:8081/api'
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
        const response = await priceApi.get<Product>('/prices/most-expensive');
        return response.data;
    },

    getAveragePrice: async () => {
        const response = await priceApi.get<number>('/prices/average');
        return response.data;
    }
};
