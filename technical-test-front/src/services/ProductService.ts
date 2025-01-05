import axios from 'axios';
import { Product } from '../types/Product';

const PRODUCT_API_URL = 'http://localhost:8080/api/products';
const PRICE_API_URL = 'http://localhost:8081/api/prices';

export const ProductService = {
    // Product CRUD operations
    getAllProducts: async (): Promise<Product[]> => {
        const response = await axios.get(PRODUCT_API_URL);
        return response.data;
    },

    getProduct: async (id: number): Promise<Product> => {
        const response = await axios.get(`${PRODUCT_API_URL}/${id}`);
        return response.data;
    },

    createProduct: async (product: Omit<Product, 'id'>): Promise<Product> => {
        const response = await axios.post(PRODUCT_API_URL, product);
        return response.data;
    },

    updateProduct: async (id: number, product: Product): Promise<Product> => {
        const response = await axios.put(`${PRODUCT_API_URL}/${id}`, product);
        return response.data;
    },

    deleteProduct: async (id: number): Promise<void> => {
        await axios.delete(`${PRODUCT_API_URL}/${id}`);
    },

    // Price-related operations (from price-service)
    getMostExpensiveProduct: async (): Promise<Product> => {
        const response = await axios.get(`${PRICE_API_URL}/most-expensive`);
        return response.data;
    },

    getAveragePrice: async (): Promise<number> => {
        const response = await axios.get(`${PRICE_API_URL}/average`);
        return response.data;
    },

    calculatePrice: async (request: any): Promise<any> => {
        const response = await axios.post(`${PRICE_API_URL}/calculate`, request);
        return response.data;
    }
};
