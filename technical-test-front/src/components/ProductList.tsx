import { useEffect, useState } from 'react';
import { Product } from '../types/Product';
import { ProductService } from '../services/api';
import { 
    Table, TableBody, TableCell, TableContainer, 
    TableHead, TableRow, Paper, IconButton,
    Typography, Box
} from '@mui/material';
import DeleteIcon from '@mui/icons-material/Delete';
import EditIcon from '@mui/icons-material/Edit';

interface ProductListProps {
    onEdit: (product: Product) => void;
}

export const ProductList = ({ onEdit }: ProductListProps) => {
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
    }, []);

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
                            <TableCell>Price</TableCell>
                            <TableCell>On Sale</TableCell>
                            <TableCell>Details</TableCell>
                            <TableCell>Actions</TableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {products.map((product) => (
                            <TableRow key={product.id}>
                                <TableCell>{product.name}</TableCell>
                                <TableCell>{product['@type']}</TableCell>
                                <TableCell>${product.price.toFixed(2)}</TableCell>
                                <TableCell>{product.onSale ? 'Yes' : 'No'}</TableCell>
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
                        ))}
                    </TableBody>
                </Table>
            </TableContainer>
        </Box>
    );
};
