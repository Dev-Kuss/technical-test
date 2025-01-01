import axios from 'axios';
import { Product } from '../types/Product';

const api = axios.create({
    baseURL: 'http://localhost:8080/api'
});

export const ProductService = {
    getAllProducts: async () => {
        const response = await api.get<Product[]>('/products');
        return response.data;
    },

    getProduct: async (id: number) => {
        const response = await api.get<Product>(`/products/${id}`);
        return response.data;
    },

    createProduct: async (product: Omit<Product, 'id'>) => {
        const response = await api.post<Product>('/products', product);
        return response.data;
    },

    updateProduct: async (id: number, product: Product) => {
        const response = await api.put<Product>(`/products/${id}`, product);
        return response.data;
    },

    deleteProduct: async (id: number) => {
        await api.delete(`/products/${id}`);
    },

    getMostExpensiveProduct: async () => {
        const response = await api.get<Product>('/products/most-expensive');
        return response.data;
    },

    getAveragePrice: async () => {
        const response = await api.get<number>('/products/average-price');
        return response.data;
    }
};
