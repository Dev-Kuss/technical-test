import React, { useEffect, useState } from 'react';
import { Notification, NotificationService } from '../services/NotificationService';
import { Alert, Snackbar } from '@mui/material';

export const NotificationToast: React.FC = () => {
    const [notification, setNotification] = useState<Notification | null>(null);
    const [open, setOpen] = useState(false);

    useEffect(() => {
        const notificationService = NotificationService.getInstance();
        const unsubscribe = notificationService.subscribe((newNotification) => {
            setNotification(newNotification);
            setOpen(true);
        });

        return () => {
            unsubscribe();
            notificationService.disconnect();
        };
    }, []);

    const handleClose = () => {
        setOpen(false);
    };

    const getSeverity = (type: string) => {
        switch (type) {
            case 'SUCCESS':
                return 'success';
            case 'WARNING':
                return 'warning';
            case 'ERROR':
                return 'error';
            default:
                return 'info';
        }
    };

    if (!notification) return null;

    return (
        <Snackbar
            open={open}
            autoHideDuration={6000}
            onClose={handleClose}
            anchorOrigin={{ vertical: 'top', horizontal: 'right' }}
        >
            <Alert
                onClose={handleClose}
                severity={getSeverity(notification.type)}
                variant="filled"
            >
                {notification.message}
            </Alert>
        </Snackbar>
    );
};
