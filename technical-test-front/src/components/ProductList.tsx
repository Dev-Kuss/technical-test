import { useEffect, useState } from 'react';
import { Product } from '../types/Product';
import { ProductService } from '../services/api';
import { 
    Table, TableBody, TableCell, TableContainer, 
    TableHead, TableRow, Paper, IconButton,
    Typography, Box, Chip
} from '@mui/material';
import DeleteIcon from '@mui/icons-material/Delete';
import EditIcon from '@mui/icons-material/Edit';

interface ProductListProps {
    onEdit: (product: Product) => void;
    refreshTrigger?: number;
}

const calculateShippingCost = (product: Product): number => {
    if ('weight' in product) {
        return product.weight * 10;
    }
    return 0;
};

const calculateDiscountedPrice = (price: number, onSale: boolean): number => {
    return onSale ? price * 0.9 : price;
};

const calculateTotalPrice = (product: Product): number => {
    const basePrice = calculateDiscountedPrice(product.price, product.onSale);
    const shippingCost = calculateShippingCost(product);
    return basePrice + shippingCost;
};

export const ProductList = ({ onEdit, refreshTrigger }: ProductListProps) => {
    const [products, setProducts] = useState<Product[]>([]);
    const [averagePrice, setAveragePrice] = useState<number>(0);
    const [mostExpensive, setMostExpensive] = useState<Product | null>(null);

    const loadProducts = async () => {
        try {
            const [productsData, avgPrice, expensiveProduct] = await Promise.all([
                ProductService.getAllProducts(),
                ProductService.getAveragePrice(),
                ProductService.getMostExpensiveProduct()
            ]);
            setProducts(productsData);
            setAveragePrice(avgPrice);
            setMostExpensive(expensiveProduct);
        } catch (error) {
            console.error('Error loading products:', error);
        }
    };

    useEffect(() => {
        loadProducts();
    }, [refreshTrigger]);

    const handleDelete = async (id: number) => {
        try {
            await ProductService.deleteProduct(id);
            await loadProducts();
        } catch (error) {
            console.error('Error deleting product:', error);
        }
    };

    return (
        <Box sx={{ width: '100%', mt: 3 }}>
            <Box sx={{ mb: 3 }}>
                <Typography variant="h6" gutterBottom>
                    Statistics
                </Typography>
                <Typography>
                    Average Price: ${averagePrice.toFixed(2)}
                </Typography>
                {mostExpensive && (
                    <Typography>
                        Most Expensive: {mostExpensive.name} (${mostExpensive.price.toFixed(2)})
                    </Typography>
                )}
            </Box>

            <TableContainer component={Paper}>
                <Table>
                    <TableHead>
                        <TableRow>
                            <TableCell>Name</TableCell>
                            <TableCell>Type</TableCell>
                            <TableCell>Base Price</TableCell>
                            <TableCell>On Sale</TableCell>
                            <TableCell>Discounted Price</TableCell>
                            <TableCell>Shipping Cost</TableCell>
                            <TableCell>Total Price</TableCell>
                            <TableCell>Details</TableCell>
                            <TableCell>Actions</TableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {products.map((product) => {
                            const shippingCost = calculateShippingCost(product);
                            const discountedPrice = calculateDiscountedPrice(product.price, product.onSale);
                            const totalPrice = calculateTotalPrice(product);
                            
                            return (
                                <TableRow 
                                    key={product.id}
                                    sx={product.onSale ? { 
                                        backgroundColor: 'rgba(76, 175, 80, 0.08)'
                                    } : {}}
                                >
                                    <TableCell>{product.name}</TableCell>
                                    <TableCell>{product['@type']}</TableCell>
                                    <TableCell>${product.price.toFixed(2)}</TableCell>
                                    <TableCell>
                                        {product.onSale ? (
                                            <Chip 
                                                label="On Sale!" 
                                                color="success" 
                                                size="small"
                                            />
                                        ) : 'No'}
                                    </TableCell>
                                    <TableCell>
                                        {product.onSale ? (
                                            <Typography color="success.main">
                                                ${discountedPrice.toFixed(2)}
                                            </Typography>
                                        ) : '-'}
                                    </TableCell>
                                    <TableCell>
                                        {'weight' in product ? `$${shippingCost.toFixed(2)}` : '-'}
                                    </TableCell>
                                    <TableCell>
                                        <Typography
                                            color={product.onSale ? 'success.main' : 'inherit'}
                                            fontWeight={product.onSale ? 'bold' : 'normal'}
                                        >
                                            ${totalPrice.toFixed(2)}
                                        </Typography>
                                    </TableCell>
                                    <TableCell>
                                        {'weight' in product 
                                            ? `Weight: ${product.weight}kg`
                                            : `Size: ${product.sizeMB}MB`
                                        }
                                    </TableCell>
                                    <TableCell>
                                        <IconButton onClick={() => onEdit(product)}>
                                            <EditIcon />
                                        </IconButton>
                                        <IconButton onClick={() => product.id && handleDelete(product.id)}>
                                            <DeleteIcon />
                                        </IconButton>
                                    </TableCell>
                                </TableRow>
                            );
                        })}
                    </TableBody>
                </Table>
            </TableContainer>
        </Box>
    );
};
