import axios from 'axios';
import { Product } from '../types/Product';

const API_URL = import.meta.env.VITE_API_URL || '/api';

const productApi = axios.create({
    baseURL: API_URL + '/products'
});

const priceApi = axios.create({
    baseURL: API_URL + '/prices'
});

export const ProductService = {
    getAllProducts: async () => {
        try {
            console.log('Fetching all products...');
            const response = await productApi.get<Product[]>('');  
            console.log('Products response:', response);
            return response.data;
        } catch (error) {
            console.error('Error fetching products:', error);
            return [];
        }
    },

    getProduct: async (id: number) => {
        const response = await productApi.get<Product>(`/${id}`);
        return response.data;
    },

    createProduct: async (product: Omit<Product, 'id'>) => {
        const response = await productApi.post<Product>('', product);  
        return response.data;
    },

    updateProduct: async (id: number, product: Omit<Product, 'id'>) => {
        const response = await productApi.put<Product>(`/${id}`, product);
        return response.data;
    },

    deleteProduct: async (id: number) => {
        await productApi.delete(`/${id}`);
    },

    getMostExpensiveProduct: async () => {
        try {
            console.log('Fetching most expensive product...');
            const response = await priceApi.get<Product>('/most-expensive');
            console.log('Most expensive product raw response:', response);
            if (!response.data) {
                console.warn('Most expensive product response is empty');
                return null;
            }
            return response.data;
        } catch (error) {
            console.error('Error fetching most expensive product:', error);
            return null;
        }
    },

    getAveragePrice: async () => {
        try {
            console.log('Fetching average price...');
            const response = await priceApi.get<{ average: number } | number>('/average');
            console.log('Average price raw response:', response);
            
            if (!response.data) {
                console.warn('Average price response is empty');
                return 0;
            }

            if (typeof response.data === 'number') {
                console.log('Response is direct number:', response.data);
                return response.data;
            }

            if (typeof response.data === 'object' && 'average' in response.data) {
                console.log('Response is object with average:', response.data.average);
                return response.data.average;
            }

            console.error('Unexpected response format:', response.data);
            return 0;
        } catch (error) {
            console.error('Error fetching average price:', error);
            return 0;
        }
    }
};
