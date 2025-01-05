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
                    Average Price: ${averagePrice.toFixed(2)}
                </Typography>
                {mostExpensive && (
                    <Typography variant="subtitle1">
                        Most Expensive Product: {mostExpensive.name} (${calculateTotalPrice(mostExpensive).toFixed(2)})
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
                                <TableCell>${product.price.toFixed(2)}</TableCell>
                                <TableCell>${calculateShippingCost(product).toFixed(2)}</TableCell>
                                <TableCell>${calculateTotalPrice(product).toFixed(2)}</TableCell>
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
