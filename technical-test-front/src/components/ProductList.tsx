import { useEffect, useState } from 'react';
import { Product } from '../types/Product';
import { ProductService } from '../services/api';
import { 
    Table, TableBody, TableCell, TableContainer, 
    TableHead, TableRow, Paper, IconButton,
    Typography, Box, Chip, Dialog, DialogTitle,
    DialogContent, DialogActions, Button
} from '@mui/material';
import DeleteIcon from '@mui/icons-material/Delete';
import EditIcon from '@mui/icons-material/Edit';

interface ProductListProps {
    onEditProduct: (product: Product) => void;
    refreshTrigger?: number;
}

const calculateShippingCost = (product: Product): number => {
    if (product.product_type === 'PHYSICAL' && 'weight' in product) {
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

export const ProductList = ({ onEditProduct, refreshTrigger }: ProductListProps) => {
    const [products, setProducts] = useState<Product[]>([]);
    const [averagePrice, setAveragePrice] = useState<number>(0);
    const [mostExpensive, setMostExpensive] = useState<Product | null>(null);
    const [deleteDialogOpen, setDeleteDialogOpen] = useState(false);
    const [productToDelete, setProductToDelete] = useState<number | null>(null);

    const loadProducts = async () => {
        try {
            console.log('Loading products...');
            const productsData = await ProductService.getAllProducts();
            console.log('Products loaded:', productsData);
            setProducts(productsData);
            
            try {
                console.log('Loading average price...');
                const avgPrice = await ProductService.getAveragePrice();
                console.log('Average price loaded:', avgPrice, typeof avgPrice);
                if (avgPrice === 0) {
                    console.warn('Average price is 0, this might be incorrect');
                }
                setAveragePrice(avgPrice);
                console.log('Average price state updated:', avgPrice);
            } catch (error) {
                console.error('Error loading average price:', error);
                setAveragePrice(0);
            }
            
            try {
                console.log('Loading most expensive product...');
                const expensiveProduct = await ProductService.getMostExpensiveProduct();
                console.log('Most expensive product loaded:', expensiveProduct);
                if (!expensiveProduct) {
                    console.warn('Most expensive product is null');
                }
                setMostExpensive(expensiveProduct);
                if (expensiveProduct) {
                    console.log('Most expensive product price:', expensiveProduct.price);
                }
            } catch (error) {
                console.error('Error loading most expensive product:', error);
                setMostExpensive(null);
            }
        } catch (error) {
            console.error('Error loading products:', error);
            setProducts([]);
            setAveragePrice(0);
            setMostExpensive(null);
        }
    };

    const formatPrice = (price: number | null | undefined): string => {
        console.log('Formatting price:', price, typeof price);
        const num = Number(price);
        console.log('Converted to number:', num, typeof num);
        const formatted = isNaN(num) ? '0.00' : num.toFixed(2);
        console.log('Formatted price:', formatted);
        return formatted;
    };

    useEffect(() => {
        console.log('ProductList mounted or refreshTrigger changed');
        loadProducts();
    }, [refreshTrigger]);

    useEffect(() => {
        console.log('Statistics updated - Average Price:', averagePrice);
        console.log('Statistics updated - Most Expensive:', mostExpensive);
    }, [averagePrice, mostExpensive]);

    const handleDeleteClick = (id: number) => {
        setProductToDelete(id);
        setDeleteDialogOpen(true);
    };

    const handleDeleteConfirm = async () => {
        if (productToDelete !== null) {
            try {
                await ProductService.deleteProduct(productToDelete);
                await loadProducts();
            } catch (error) {
                console.error('Error deleting product:', error);
            }
        }
        setDeleteDialogOpen(false);
        setProductToDelete(null);
    };

    const handleDeleteCancel = () => {
        setDeleteDialogOpen(false);
        setProductToDelete(null);
    };

    return (
        <Box sx={{ width: '100%', mt: 3 }}>
            <Box sx={{ mb: 3 }}>
                <Typography variant="h6" gutterBottom>
                    Statistics
                </Typography>
                <Typography variant="subtitle1">
                    Average Price: ${formatPrice(averagePrice)}
                </Typography>
                {mostExpensive && (
                    <Typography variant="subtitle1">
                        Most Expensive Product: {mostExpensive.name} (${formatPrice(calculateTotalPrice(mostExpensive))})
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
                            <TableCell>Shipping</TableCell>
                            <TableCell>Total Price</TableCell>
                            <TableCell>Status</TableCell>
                            <TableCell>Actions</TableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {products.map((product) => (
                            <TableRow key={product.id}>
                                <TableCell>{product.name}</TableCell>
                                <TableCell>{product.product_type}</TableCell>
                                <TableCell>${formatPrice(product.price)}</TableCell>
                                <TableCell>${formatPrice(calculateShippingCost(product))}</TableCell>
                                <TableCell>${formatPrice(calculateTotalPrice(product))}</TableCell>
                                <TableCell>
                                    <Chip 
                                        label={product.onSale ? "On Sale" : "Regular Price"}
                                        color={product.onSale ? "success" : "default"}
                                    />
                                </TableCell>
                                <TableCell>
                                    <IconButton onClick={() => onEditProduct(product)} color="primary">
                                        <EditIcon />
                                    </IconButton>
                                    <IconButton onClick={() => handleDeleteClick(product.id!)} color="error">
                                        <DeleteIcon />
                                    </IconButton>
                                </TableCell>
                            </TableRow>
                        ))}
                    </TableBody>
                </Table>
            </TableContainer>

            <Dialog
                open={deleteDialogOpen}
                onClose={handleDeleteCancel}
            >
                <DialogTitle>Confirm Delete</DialogTitle>
                <DialogContent>
                    Are you sure you want to delete this product?
                </DialogContent>
                <DialogActions>
                    <Button onClick={handleDeleteCancel}>Cancel</Button>
                    <Button onClick={handleDeleteConfirm} color="error">Delete</Button>
                </DialogActions>
            </Dialog>
        </Box>
    );
};
